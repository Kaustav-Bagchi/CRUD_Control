import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CRUD_Control
{
	private static ConcurrentHashMap<Integer,String> Request = new ConcurrentHashMap<>(); //Incoming Requests stored here
	static ConcurrentHashMap<Integer,String> Response = new ConcurrentHashMap<>();  //GET method reads and deleted from here
	static final long heap_Threshold=(Runtime.getRuntime().totalMemory())/4; //Threshold value(Tune according to needs)
	static long count_of_RequestsinFile=0; //No of map objects that has spilled onto disk
	

	
	private static volatile CRUD_Control instance;
	private CRUD_Control() { 
	
		int test=0
	    
	}
	
	public static CRUD_Control getInstance() 
	{
	   	if (instance == null ) 
			{
	      			instance = new CRUD_Control();
	  		}

	    	return instance;
	}	


	public static int ItemsonDisk() //To execute logic based on no. of files awaiting in disk
	{
		String folder_name="/tmp/Requests";
		File folder=new File(folder_name);	
		File[] listOfFiles=folder.listFiles();
		return listOfFiles.length;
	}


	public static synchronized String PUT(int ID,String Data)
	{
		String returncode="";
		Runnable runnable = () ->{
			CRUD_Control.load_requests();
		};
		Thread thread=new Thread(runnable);

		if(count_of_RequestsinFile > 0)
		{
			if(CRUD_Control.usedMemory() >= heap_Threshold)
			{
				System.out.println("Backend not processing. Heap space still high. Writing requests to File");
			}
			else
			{
				System.out.println("Loading data from disk onto Map");
				
				thread.start(); //Heap space analyzed before PUT. If available, then spawn a thread to load files onto map
				
			}
		}
		
		try
		{
						
			if(CRUD_Control.usedMemory() >= heap_Threshold){	
				throw new OutOfMemoryError("Exceeded heap"); //Either customize OutOfMemory Error of let JVM throw it
			}
			else
			{
				returncode="200 OK";
				Request.putIfAbsent(ID,Data); //If heap under control, do normal PUT
			}
		}
		catch(OutOfMemoryError e)
		{
			System.out.println("Writing Data for id "+ID+"onto disk");
			++count_of_RequestsinFile;
			CRUD_Control.write_Request_toFile(ID,Data); //Write incoming requests onto a single file
			returncode="200 Slowness at Backend.Expect delay in response.Sorry for the inconvenience";
		}
		catch(Exception e)
		{
			returncode="400 Bad Request";
			System.out.println(e.toString());
		}
		
		return returncode;
	}

	public static String GET(int search)
	{
		 String Result=""; //Searching and deleting records. Reponse needs to be transported via JMS, not stored in Map
		 try{
			Result=Response.search(16,(k,v) -> {
			if(k.equals(search))
			{
				return Response.remove(k);	
			}
			return "N/A";
		});
		    }
		catch(Exception e){System.out.println(e.toString());}
	
		if(Result.isEmpty())
		{
			return "404 Not Found";
		}
		else
		{
			return Result;
		}

	}

	public String process_data(int ID)
	{
		
		/*
		try
		{		
			Response.put(ID,Data);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		*/

		return Request.remove(ID); //Write any business logic here, for now,Requests are deleted based on ID
	}

	public static void print_Request() //Printing Request Map
	{
		for (Map.Entry<Integer,String> entry : Request.entrySet()){
			System.out.println(entry.getKey().toString()+" : "+entry.getValue());		
		}		
	}

	public static void print_Response() //Printing Response Map
	{
		for (Map.Entry<Integer,String> entry : Response.entrySet()){
			System.out.println(entry.getKey().toString()+" : "+entry.getValue());		
		}
	}

	private static long usedMemory() //Get Used Meory during Runtime
	{
		return Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	}


	private static void write_Request_toFile(int ID,String Data)
	{
		try{
		String file_name="";
		file_name="/tmp/Requests/"+ID+".txt";
		Path path=Paths.get(file_name);
		try(BufferedWriter writer=Files.newBufferedWriter(path))
		{
			writer.write(Data);
		}}catch(Exception e){System.out.println("Exception while writing to file "+e.toString());} //Write incoming Requests in a file in Thread - safe maner
		
	}


	private synchronized static void load_requests()
	{
		String folder_name="/tmp/Requests";
		String temp_file_name="";
		File folder=new File(folder_name);	
		String Data="";
		
		ArrayList<String> file_names=new ArrayList<String>();
			
			File[] listOfFiles=folder.listFiles();
			
			
			try
			{
			for(int i=0;i<listOfFiles.length;i++)
			{
				if(listOfFiles[i].isFile())
				{
					file_names.add(listOfFiles[i].getName()); //List files in the specified directory
						
				}
			}}catch(Exception listing){System.out.println("Exception while listing files "+listing.toString());}
			
			Iterator itr=file_names.iterator();
			//Iterate over files and check each time if there is memory available. If yes, load in map and delete file. Else Break
			
			try{
			while(itr.hasNext()) 
			{
				String ID=itr.next().toString();
				if(CRUD_Control.usedMemory() < heap_Threshold)
				{
					temp_file_name=folder+"/"+ID;	
					try{ 
					Data=new String(Files.readAllBytes(Paths.get(temp_file_name)));
					File file = new File(temp_file_name);
					System.out.println("File "+temp_file_name+" status of deletion : "+file.delete());
					}catch(Exception e){System.out.println("Exception while writing or deleting "+e.toString());}
					System.out.println("ID = "+ID+" , Data ="+Data+" has been loaded onto Map");
					Request.putIfAbsent(Integer.parseInt(ID),Data);
					count_of_RequestsinFile--;
				}
				else{break;}
			}
			}catch(Exception E){}
		
		
	}

}
