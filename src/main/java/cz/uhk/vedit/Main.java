package cz.uhk.vedit;

import cz.uhk.vedit.gui.VeditFrame;

import javax.swing.*;

public class Main {
    static void main(String[] args) {
        //spusteni gui
        SwingUtilities.invokeLater(
                ()->new VeditFrame().setVisible(true)
        );
    }
}
