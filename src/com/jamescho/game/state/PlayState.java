package com.jamescho.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.model.Block;
import com.jamescho.game.model.Cloud;
import com.jamescho.game.model.Player;

public class PlayState extends State {
    private Player player;
    private ArrayList<Block> blocks;
    private Cloud cloud1, cloud2;
    private Font scoreFont, pauseFont;
    private int playScore = 0;

    private boolean isPaused;

    private static final int BLOCK_WIDTH = 20;
    private static final int BLOCK_HEIGHT = 50;
    private int blockSpeed = -200;

    private static final int PLAYER_WIDTH = 66;
    private static final int PLAYER_HEIGHT = 92;

    @Override
    public void init() {
        System.out.println("init() " + getClass().getCanonicalName());

        player = new Player(160, GameMain.GAME_HEIGHT - 45 - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        blocks = new ArrayList<Block>();
        cloud1 = new Cloud(100, 50);
        cloud2 = new Cloud(500, 50);
        scoreFont = new Font("SansSerif", Font.BOLD, 25);
        pauseFont = new Font("SansSerif", Font.BOLD, 25);
        isPaused = false;

        for(int i = 0; i < 5; i++) {
            Block b = new Block(i*200, GameMain.GAME_HEIGHT - 95, BLOCK_WIDTH, BLOCK_HEIGHT);
            blocks.add(b);
        }
    }

    @Override
    public void update(float delta) {
        System.out.println("update() " + getClass().getCanonicalName());

        if(!isPaused) {
            if(!player.isAlive()) {
                setCurrentState(new GameOverState(playScore / 100));
            }
            playScore += 1;
            if(playScore % 500 == 0 && blockSpeed > -280) {
                blockSpeed -= 10;
            }

            cloud1.update(delta);
            cloud2.update(delta);
            Resources.runAnim.update(delta);
            player.update(delta);
            updateBlocks(delta);
        }
    }

    private void updateBlocks(float delta) {
        for(Block b: blocks) {
            b.update(delta, blockSpeed);

            if(b.isVisible()) {
                if(player.isDucked() && b.getRect().intersects(player.getDuckRect())) {
                    b.onCollide(player);
                }else if(!player.isDucked() && b.getRect().intersects(player.getRect())) {
                    b.onCollide(player);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Resources.skyBlue);
        g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        renderPlayer(g);
        renderBlocks(g);
        renderSun(g);
        renderClouds(g);
        g.drawImage(Resources.grass, 0, 405, null);
        renderScore(g);

        if(isPaused) {
//            g.setColor(Color.DARK_GRAY);
            g.setFont(pauseFont);
            g.drawString("Pause", 390, 175);
        }
    }

    private void renderScore(Graphics g) {
        g.setFont(scoreFont);
        g.setColor(Color.GRAY);
        g.drawString(Integer.toString(playScore / 100), 20, 30);
    }

    private void renderClouds(Graphics g) {
        g.drawImage(Resources.cloud1, (int)cloud1.getX(), (int)cloud1.getY(), 100, 60, null);
        g.drawImage(Resources.cloud2, (int)cloud2.getX(), (int)cloud2.getY(), 100, 60, null);
    }

    private void renderSun(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(625, 40, 100, 100);
        g.setColor(Color.YELLOW);
        g.fillOval(630, 45, 90, 90);
    }

    private void renderBlocks(Graphics g) {
        for(Block b: blocks) {
            if(b.isVisible()) {
                g.drawImage(Resources.block, (int)b.getX(), (int)b.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
            }
        }
    }

    private void renderPlayer(Graphics g) {
        if(player.isGrounded()) {
            if(player.isDucked()) {
                g.drawImage(Resources.duck, (int)player.getX(), (int)player.getY(), null);
            }else {
                Resources.runAnim.render(g, (int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight());
            }
        }else {
            g.drawImage(Resources.jump, (int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight(), null);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        System.out.println("onClick() " + getClass().getCanonicalName());
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        System.out.println("onKeyPress() " + getClass().getCanonicalName());

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_P) {
            isPaused = !isPaused;
        }else if(key == KeyEvent.VK_ESCAPE) {
            setCurrentState(new GameOverState(playScore / 100));
        }else if(!isPaused) {
            if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
                player.jump();
            }else if(key == KeyEvent.VK_DOWN) {
                player.duck();
            }
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        System.out.println("onKeyRelease() " + getClass().getCanonicalName());
    }
}
