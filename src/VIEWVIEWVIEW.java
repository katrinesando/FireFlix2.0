import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class VIEWVIEWVIEW {

    public VIEWVIEWVIEW(){}

    public static void main (String[] args){
        makeFrame();
    }

    private static void makeFrame(){
        JFrame frame = new JFrame("FireFlix");
        frame.setSize(700, 700);
        Container contentPane = frame.getContentPane();

        JLabel jLabel = new JLabel("Jlist");
        contentPane.add(jLabel);

        frame.pack();
        frame.setVisible(true);
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu filmMenu = new JMenu("Film");
        JMenu seriesMenu = new JMenu("Serier");
        JMenu genreMenu = new JMenu("Genre");
        JMenu userMenu = new JMenu("Min bruger");
        menubar.add(filmMenu);
        menubar.add(seriesMenu);
        menubar.add(genreMenu);
        menubar.add(userMenu);


        JMenuItem filmItem = new JMenuItem("Film");
        filmItem.addActionListener((ActionEvent e) -> System.out.println("film"));
        filmMenu.add(filmItem);

        JMenuItem seriesItem = new JMenuItem("Serier");
        seriesItem.addActionListener((ActionEvent e) -> System.out.println("serier"));
        seriesMenu.add(seriesItem);

        JMenuItem userItem = new JMenuItem("Brugere");
        userItem.addActionListener(VIEWVIEWVIEW::actionPerformed);
        userMenu.add(userItem);

        JMenuItem crimeItem = new JMenuItem("Crime");
        genreMenu.add(crimeItem);
        JMenuItem thrillerItem = new JMenuItem("Thriller");
        genreMenu.add(thrillerItem);
        JMenuItem comedyItem = new JMenuItem("Comedy");
        genreMenu.add(comedyItem);
        JMenuItem adventureItem = new JMenuItem("Adventure");
        genreMenu.add(adventureItem);


    }

    private static void actionPerformed(ActionEvent e) {
        System.out.println("brugere");
    }
}
