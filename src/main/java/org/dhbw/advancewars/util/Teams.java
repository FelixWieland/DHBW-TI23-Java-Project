package org.dhbw.advancewars.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.dhbw.advancewars.level.Level;

public class Teams {
    final private String[] teams;
    private int currentTeam = 0;

    private boolean winner = false;

    public Teams(String[] names) {
        this.teams = names;
    }

    public String getCurrentTeam() {
        return teams[currentTeam];
    }

    public void endTurn() {
        if (currentTeam == teams.length-1) {
            currentTeam = 0;
        } else {
            currentTeam++;
        }
    }

    public void markWinner() {
        this.winner = true;
    }

    public void renderWinnerScreen(GraphicsContext ctx, String winningTeam) {
        ctx.setFill(Color.BLACK);
        ctx.fillRect(0, 0, ctx.getCanvas().getWidth(), ctx.getCanvas().getHeight());

        String message = "Team " + winningTeam + " won!";

        Font font = Font.font("Arial", FontWeight.BOLD, 36);
        ctx.setFont(font);
        ctx.setFill(Color.WHITE);

        Text text = new Text(message);
        text.setFont(font);
        double textWidth = text.getLayoutBounds().getWidth();
        double textHeight = text.getLayoutBounds().getHeight();

        double x = (ctx.getCanvas().getWidth() - textWidth) / 2;
        double y = (ctx.getCanvas().getHeight() - textHeight) / 2 + textHeight;

        ctx.fillText(message, x, y);
    }

    public void render(GraphicsContext ctx) {
        String name = this.getCurrentTeam();
        ctx.setFill(switch (name) {
            case "blue" -> Color.BLUE;
            case "red" -> Color.RED;
            case "yellow" -> Color.YELLOW;
            case "green" -> Color.GREEN;
            default -> Color.BEIGE;
        });

        if (winner) {
            renderWinnerScreen(ctx, name);
        } else {
            ctx.fillRect(0, 0, 200, 50);

            // Rand um den Hintergrund zeichnen
            ctx.setStroke(Color.BLACK);
            ctx.strokeRect(0, 0, 200, 50);

            // Teamnamen zeichnen
            ctx.setFill(Color.WHITE);
            ctx.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            ctx.fillText("Team " + name, 10, 35);
        }
    }
}
