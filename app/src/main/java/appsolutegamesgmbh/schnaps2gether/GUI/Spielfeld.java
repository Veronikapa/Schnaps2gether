package appsolutegamesgmbh.schnaps2gether.GUI;

/**
 * Created by kirederf on 04.06.2015.
 */
public interface Spielfeld {
    public void receiveFromLobby(String endpointID, byte[] payload, boolean isReliable);
}
