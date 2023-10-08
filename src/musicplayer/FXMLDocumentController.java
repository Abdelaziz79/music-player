/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Kking
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Label playerM;
    @FXML
    private Button playB, pauseB, nextB, perviousB, resetB;
    @FXML
    private ComboBox<String> speedB;
    @FXML
    private Slider volum;
    @FXML
    private ProgressBar songProgress;

    private Media media;
    private MediaPlayer mediaPlayer;

    private File dir;
    private File[] files;

    private ArrayList<File> songs;

    private int songN;
    private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};

    private Timer timer;
    private TimerTask task;

    private boolean running;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        songs = new ArrayList<File>();
        dir = new File("music");
        files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        }

        media = new Media(songs.get(songN).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        playerM.setText(songs.get(songN).getName());

        for (int i = 0; i < speeds.length; i++) {
            speedB.getItems().add(Integer.toString(speeds[i]) + "%");
        }

        speedB.setOnAction(this::speedM);

        volum.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volum.getValue() * 0.01);
            }
        });

    }

    public void playM() {
        beginTimer();
        speedM(null);
        mediaPlayer.setVolume(volum.getValue() * 0.01);
        mediaPlayer.play();
    }

    public void pauseM() {
        cancelTimer();
        mediaPlayer.pause();

    }

    public void resetM() {
        songProgress.setProgress(0.0);
        mediaPlayer.seek(Duration.seconds(0.0));
    }

    public void perviousM() {
        if (songN > 0) {
            songN--;
            mediaPlayer.stop();

            if (running) {
                cancelTimer();
            }

            media = new Media(songs.get(songN).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            playerM.setText(songs.get(songN).getName());
            playM();

        } else {

            songN = songs.size() - 1;
            mediaPlayer.stop();

            if (running) {
                cancelTimer();
            }

            media = new Media(songs.get(songN).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            playerM.setText(songs.get(songN).getName());
            playM();
        }

    }

    public void nextM() {
        if (songN < songs.size() - 1) {
            songN++;
            mediaPlayer.stop();

            if (running) {
                cancelTimer();
            }

            media = new Media(songs.get(songN).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            playerM.setText(songs.get(songN).getName());
            playM();

        } else {
            songN = 0;
            mediaPlayer.stop();

            if (running) {
                cancelTimer();
            }

            media = new Media(songs.get(songN).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            playerM.setText(songs.get(songN).getName());
            playM();
        }

    }

    public void speedM(ActionEvent event) {

        if (speedB.getValue() == null) {
            mediaPlayer.setRate(1.0);
        } else {
            mediaPlayer.setRate(Integer.parseInt(speedB.getValue().substring(0, speedB.getValue().length() - 1)) * 0.01);
        }

    }

    public void beginTimer() {

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double cur = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgress.setProgress(cur / end);
                if (cur / end == 1) {
                    cancelTimer();
                }

            }

        };

        timer.scheduleAtFixedRate(task, 0, 1000);

    }

    public void cancelTimer() {
        running = false;
        timer.cancel();

    }

}
