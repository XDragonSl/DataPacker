package com.dpapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import com.dp.DataInfo;
import com.dp.DataReader;
import com.dp.DataWriter;

public class Main {
	private static final long PAUSE = 100;
	
	public static void main(String args[]) {
		System.out.println("Data Packer\nVersion 1.1.1\n");
		System.out.println("Created in 2019\nBased on DP v 3 library\n");
		System.out.println("Developer: Ermolenko V.A.");
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			System.out.println("\nFor viewing press \"v\"\nFor packing press \"p\"\nFor unpacking press \"u\"\nFor exit press \"e\"\n");
			String ans = new String("");
			while(ans.equals("")) {
				System.out.print("> ");
				ans = sc.nextLine();
			}
			switch(ans) {
			case "v":
				System.out.print("\nInput packname: ");
				String packname = sc.nextLine();
				try {
					long size[] = DataInfo.getSizes(packname);
					String file[] = DataInfo.getList(packname);
					for(int i = 0; i < file.length; i++) {
						System.out.print("\n" + file[i] + " (" + size[i] + " b)");
					}
					System.out.println();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				break;
			case "p":
				System.out.print("\nInput listname: ");
				String listname = sc.nextLine();
				System.out.print("Input packname: ");
				packname = sc.nextLine();
				try {
					DataWriter.writeFileFromList(listname, packname);
					System.out.println("\nReady!");
				} catch (IOException e) {
					e.printStackTrace();
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				break;
			case "u":
				System.out.print("\nInput packname: ");
				packname = sc.nextLine();
				System.out.print("Input filename: ");
				String filename = sc.nextLine();
				try {
					InputStream is = DataReader.readFile(filename, packname);
					if (new File(filename + ".dpacktmp").exists()) {
						new File(filename + ".dpacktmp").renameTo(new File(filename));
					} else {
						PrintStream ps = new PrintStream(filename);
						byte file[] = new byte[is.available()];
						is.read(file);
						ps.write(file);
						ps.close();
					}
					is.close();
					System.out.println("\nReady!");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				break;
			case "e":
				exit = true;
				break;
			default:
				System.out.println("\nIllegal answer \"" + ans + "\"");
				break;
			}
		}
		sc.close();
	}
}
