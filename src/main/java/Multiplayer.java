/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import bowling.SinglePlayerGame;
import bowling.MultiPlayerGame;

/**
 *
 * @author Axel
 */
public class Multiplayer implements MultiPlayerGame{
    
    public SinglePlayerGame[] games;
    public String[] players;
    public int turn;
    public int currentPlayer;

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        if (playerName == null || playerName.length == 0){
            throw new UnsupportedOperationException("Liste de joueurs vide ou nulle");
        }
        this.players = playerName;
        this.games = new SinglePlayerGame[playerName.length];
        for (int i=0;i<this.games.length;i++){
            this.games[i] = new SinglePlayerGame();
        }
        this.turn = 0;
        this.currentPlayer = 0;
        return "Prochain tir : joueur "+this.players[0]+", tour n° 1, boule n° 1";
    }
    
    public void nextTurn(){
        if (currentPlayer == this.players.length-1){
                this.currentPlayer = 0;
                this.turn++;
            }
            else{
                this.currentPlayer++;
            }
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (this.games == null){
            throw new UnsupportedOperationException("La partie n'as pas été démarrée");
        }
        
        if (this.games[this.currentPlayer].isFinished()){
            return "Partie terminée.";
        }
        
        this.games[currentPlayer].lancer(nombreDeQuillesAbattues);
        if (this.games[currentPlayer].hasCompletedFrame()){
            nextTurn();
        } 
        return "Prochain tir : joueur "+this.players[currentPlayer]+", tour n° "+(this.turn+1)+", boule n° "+this.games[currentPlayer].getNextBallNumber();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        int cpt = -1;
        int player;
        for (int i=0;i<this.players.length;i++){
            if (this.players[i].equals(playerName)){
               cpt = this.games[i].score();
            }
        }
        if (cpt == -1){
            throw new Exception("La personne demandé ne joue pas dans laz partie.");
        }
        return cpt; 
    }
    
}
