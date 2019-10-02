
import bowling.SinglePlayerGame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Axel
 */
public class MutiplayerTest {
    
    Multiplayer game;
    String[] players;
    
    @Before
    public void setUp() throws Exception {
        players = new String[]{"Lucie","Florian","Léa","Corentin"};
        game = new Multiplayer();
        game.startNewGame(players);
    }
        
    @Test (expected = UnsupportedOperationException.class)
    public void testEmptyPlayerList() throws Exception{
        /**
         * Test le renvoi d'une erreur si on initialise avec une liste de joueurs vide.
         */
        game.startNewGame(new String[]{});
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testNullPlayerList() throws Exception{
        /**
         * Test le renvoi d'une erreur si on initialise sans liste de joueurs.
         */
        game.startNewGame(null);
    }
    
    @Test
    public void testInitialize() throws Exception{
        /**
         * Test l'initialisation correcte d'une partie
         */
        String message = "Prochain tir : joueur Lucie, tour n° 1, boule n° 1";
        assertEquals(message,game.startNewGame(players));
    }
    
    @Test
    public void testNextBowlingBall() throws Exception{
        /**
         * Test que on passe bien à la seconde boule après un lancer non strike.
         */
        String message = "Prochain tir : joueur Lucie, tour n° 1, boule n° 2";
        assertEquals(message,game.lancer(6));
        
    }
    
    @Test
    public void testNextTurn() throws Exception{
        /**
         * Test que l'on passe bien au tour suivant
         */
        String message = "Prochain tir : joueur Florian, tour n° 1, boule n° 1";
        assertEquals(message,game.lancer(10));
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testPlayingWhenNotInitialized() throws Exception{
        /**
         * Test le renvoi d'une erreur si l'on tente de lancer une boule sur une partie non initialisée.
         */
        Multiplayer gameNull = new Multiplayer();
        gameNull.lancer(2);
        
    }
    
    @Test
    public void gameEnded() throws Exception{
        /**
         * Test que la partie se termine correctement.
         */
        rollMany(80, 1);
        assertEquals("Partie terminée.",game.lancer(1));
    }
    
    // Quelques methodes utilitaires pour faciliter l'écriture des tests
	private void rollMany(int n, int pins) throws Exception {
		for (int i = 0; i < n; i++) {
			game.lancer(pins);
		}
	}

	private void rollSpare() throws Exception {
		game.lancer(7);
		game.lancer(3);
	}

	private void rollStrike() throws Exception {
		game.lancer(10);
	}
    
        
    
}
