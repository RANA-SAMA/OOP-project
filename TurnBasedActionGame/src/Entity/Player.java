package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends entity{

    GamePanel gp;
    KeyHandler KeyH;
    private int frameCounter = 0; // Moved here

    public Player(GamePanel gp, KeyHandler KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){ x=100; y=200; speed=0; }

    public void getPlayerImage(){ try{
        a1= ImageIO.read(getClass().getResourceAsStream("/Player/sA_1.png"));
        a2= ImageIO.read(getClass().getResourceAsStream("/Player/sA_2.png"));
        a3= ImageIO.read(getClass().getResourceAsStream("/Player/sA_3.png"));
        a4= ImageIO.read(getClass().getResourceAsStream("/Player/sA_4.png"));
    } catch(IOException e){ e.printStackTrace(); } }

    public void update(){
        if(attacking) {
            frameCounter++;
            if(frameCounter >= 5) { // Control animation speed
                attackFrame++;
                frameCounter = 0;
            }
            if(attackFrame > 3) { attacking = false; attackFrame = 0; }
        }
    }

    public void handleLeftClick(){ attacking = true; attackFrame = 0; frameCounter = 0; }

    public void draw(Graphics2D g2){
        if(attacking){
            BufferedImage attackImage = switch (attackFrame) {
                case 0 -> a1;
                case 1 -> a2;
                case 2 -> a3;
                case 3 -> a4;
                default -> a4;
            };
            g2.drawImage(attackImage, x, y, gp.tileSize * 5, gp.tileSize * 5, null);
        } else if (a1 != null) {
            g2.drawImage(a1, x, y, gp.tileSize * 5, gp.tileSize * 5, null);
        }
    }
}
