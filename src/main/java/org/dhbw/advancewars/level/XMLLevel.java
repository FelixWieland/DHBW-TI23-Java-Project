package org.dhbw.advancewars.level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dhbw.advancewars.entity.*;
import org.dhbw.advancewars.util.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class XMLLevel extends Level {
    final Element root;

    public XMLLevel(String fileName) throws ParserConfigurationException, IOException, SAXException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fileName);
        document.getDocumentElement().normalize();
        this.root = document.getDocumentElement();
        this.buildLevel();
    }

    public void buildLevel() {
        this.name = root.getAttribute("name");
        this.number = Integer.parseInt(root.getAttribute("nr"));

        var rawRows = root.getChildNodes();
        List<Node> rows = IntStream.range(0, rawRows.getLength()).mapToObj(rawRows::item).filter(item -> item.getNodeName().equals("row")).toList();

        int amountOfRows = rows.size();
        int amountOfCols = 0;

        List<String> teams = new ArrayList<>();

        List<Tile> tempTiles = new ArrayList<>();
        HashMap<Position, Entity> tempEntities = new HashMap<>();

        for (int y = 0; y < amountOfRows; y++) {
            var rawTiles = rows.get(y).getChildNodes();
            List<Node> tiles = IntStream.range(0, rawTiles.getLength()).mapToObj(rawTiles::item).filter(item -> item.getNodeName().equals("tile")).toList();


            var tilesLength =  tiles.size();
            if (amountOfCols < tilesLength) {
                amountOfCols = tilesLength;
            }

            for (int x = 0; x < tilesLength; x++) {
                var items = tiles.get(x).getChildNodes();
                Tile tile = new Tile(amountOfCols-1 -  x, amountOfRows -1 - y, this);
                tempTiles.add(tile);

                for (int z = 0; z < items.getLength(); z++) {
                    var item = items.item(z);
                    var name = item.getNodeName().toLowerCase();
                    var attr = item.getAttributes();

                    String team = "";
                    if (attr != null && attr.getNamedItem("team") instanceof Node node) {
                        team = node.getNodeValue();
                        if (!team.isEmpty()) {
                            teams.add(team);
                        }
                    }

                    Position entityPos = new Position(amountOfCols-1 -  x, amountOfRows -1 - y);

                    switch (name) {
                        case "road": tile.addMapPart(Tile.MapParts.ROAD); break;
                        case "water": tile.addMapPart(Tile.MapParts.WATER); break;
                        case "grass": tile.addMapPart(Tile.MapParts.GRASS); break;
                        case "tree": tile.addMapPart(Tile.MapParts.TREE); break;
                        case "bridge": tile.addMapPart(Tile.MapParts.BRIDGE); break;
                        case "large-rock": tile.addMapPart(Tile.MapParts.LARGE_ROCK); break;
                        case "normal-rock": tile.addMapPart(Tile.MapParts.NORMAL_ROCK); break;
                        case "tiny-water-rock": tile.addMapPart(Tile.MapParts.TINY_WATER_ROCK); break;
                        case "infantry": tempEntities.put(entityPos, new Infantry(team, this)); break;
                        case "anti-air": tempEntities.put(entityPos, new AntiAir(team, this)); break;
                        case "artillery": tempEntities.put(entityPos, new Artillery(team, this)); break;
                        case "battle-copter": tempEntities.put(entityPos, new BattleCopter(team, this)); break;
                        case "bomber": tempEntities.put(entityPos, new Bomber(team, this)); break;
                        case "fighter": tempEntities.put(entityPos, new Fighter(team, this)); break;
                        case "mech-infantry": tempEntities.put(entityPos, new MechInfantry(team, this)); break;
                        case "tank": tempEntities.put(entityPos, new Tank(team, this)); break;
                    }
                }
            }
        }

        this.initMap(amountOfRows, amountOfCols);

        var distinctTeams = teams.stream().distinct().toList();
        var teamsArray = new String[(int)distinctTeams.size()];
        this.initTeams(distinctTeams.toArray(teamsArray));
        tempTiles.forEach(this::setTile);
        tempEntities.forEach(this::setEntity);
    }
}
