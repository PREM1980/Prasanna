import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Menu {

	Notebook nb1;
	Map<String,String> choices;
	public Menu() {
		super();
		this.nb1 = new Notebook();
		this.choices = new HashMap<String,String>();
	}
	
	public void display_menu(){
		System.out.println("NoteBook Menu \n 1.Show all notes \n " +
				"2.Search Notes \n 3.Add Note \n 4.Modify Note \n 5.Quit");
		
	}
	
	
	public void run(){
		while (true){
			display_menu();
			System.out.println("Enter a OPTION = ");
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String Input = null;
			try {
				Input = bf.readLine();	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Input =  " + Input);
			
			switch (Input){
				case "1":show_notes();
					break;
				case "2":search_notes();
					break;
				case "3":add_notes();print_notes();
					break;
				case "4":modify_notes();
					break;
				case "5":quit();
					break;
			}
		}	
	}
	
	public void print_notes(){
		System.out.println("Printing all notes");
		System.out.println("******************");
		for (Note n:nb1.notes)
			System.out.println(n.toString());
		System.out.println("print_Notes");
	}
	public void show_notes(){
		System.out.println("Show_Notes");
		for (Note n:nb1.notes)
			System.out.println(n.toString());
		System.out.println("Show_Notes_End");
	}
	public void search_notes(){
		System.out.println("Search_Notes");
		System.out.println("Enter a FILTER = ");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String filter = null;
		try {
			filter = bf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("print_Search_Results");
		for (Note n:nb1.search(filter))
			System.out.println(n.toString());
		
	
	}
	public void add_notes(){
		System.out.println("Add_Notes");
		
		System.out.println("Enter a MEMO = ");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String memo = null;
		try {
			memo = bf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter a TAG = ");
		BufferedReader bf1 = new BufferedReader(new InputStreamReader(System.in));
		String tags = null;
		try {
			tags = bf1.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nb1.new_note(memo, tags);
	}
	public void modify_notes(){
		System.out.println("Modify_Notes");
	}
	public void quit(){
		System.out.println("Quitting the application");
		System.exit(0);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu().run();

	}
}

class Notebook{
	Note nt1;
	List<Note> notes;
	public Notebook() {
		super();
		this.notes = new ArrayList<Note>();
	}
	public void new_note(String memo, String tags){
		this.notes.add(new Note(memo, tags));
	}
	
	public ArrayList<Note> search(String filter){
		ArrayList<Note> searchresults = new ArrayList<Note>();
		for (Note n:this.notes)
			if (n.match(filter)){
				searchresults.add(n);
			}
		return searchresults;
	}
}

class Note {
	static int id = 0;
	String memo;
	String tags;
	Date creation_date;

	public Note(String memo, String tags){
		this.memo = memo;
		this.tags = tags;
		this.creation_date = new Date();
		id += 1;
	}
	
	public boolean match(String filter){
		System.out.println("Note match filter = " + filter);
		System.out.println("Note match memo = " + this.memo);
		System.out.println(this.memo.toLowerCase().contains(filter.toLowerCase()));
		return this.memo.toLowerCase().contains(filter.toLowerCase());
	}
	public String toString(){
		return String.format("Memo = %s,  Tags =%s, id=%s",this.memo,this.tags,String.valueOf(id));
	}
	
}