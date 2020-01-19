package com.itijavafinalprojectteam8.view.interfaces;

public interface GameWithPlayerView {
    void onGetAllPlayers(String response);

    void onGameInvitationRequest(String email);

    public void confirmToast(boolean parseInvitationResponse);

    public void setGamecord(int gamecord);

    void pauseGame();

    public void handelGameResume(String textFromServer);

}
