import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class VIEWVIEWVIEW {
    private static JList list;
    public VIEWVIEWVIEW(){}

    public static void main (String[] args){
        makeFrame();
    }

    private static void makeFrame(){
        JFrame frame = new JFrame("FireFlix");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] movies1 = {"Lord of The Rings", "Star Wars", "Titanic","Indiana Jones","Love Actually","The Last Samurai"};

        list = new JList(movies1);
        JScrollPane scrollPane = new JScrollPane(list);
        Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane);


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
        seriesItem.addActionListener((ActionEvent e) -> System.out.println("brugere"));
        userMenu.add(userItem);


        JMenuItem crimeItem = new JMenuItem("Crime");
        genreMenu.add(crimeItem);
        JMenuItem thrillerItem = new JMenuItem("Thriller");
        genreMenu.add(thrillerItem);
        JMenuItem comedyItem = new JMenuItem("Comedy");
        genreMenu.add(comedyItem);
        JMenuItem adventureItem = new JMenuItem("Adventure");
        genreMenu.add(adventureItem);

        //have en liste som vi displayer  i contenpane, og vi istedet har flere forskellige list som vi bare skifter imellem

    }
}
