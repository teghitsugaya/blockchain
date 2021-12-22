package com.test.blockcain;

import hashing.Block;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlockcainApplication {

	static List<Block> blockchain = null;

	public static void main(String[] args) {
		int numberOfBlock = 100000;
		int nonce = 1342;
		String[] data = new String[]{
			"This is Our Transaction Simulation"
		};

		runningBlock(data, "MD5", numberOfBlock, nonce);
		runningBlock(data, "SHA1", numberOfBlock, nonce);
		runningBlock(data, "SHA2", numberOfBlock, nonce);
		runningBlock(data, "SHA3", numberOfBlock, nonce);
	}

	static void addBlock(Block block){
		blockchain.add(block);
	}

	static void runningBlock(String[] data, String hashMethod, int numberOfBlock, int nonce){
		blockchain = new ArrayList<>();
		double totalTime = 0;

		System.out.println("=================================================");
		System.out.println("Counter blockchain time for method "+ hashMethod);
		System.out.println("Chain start index: "+ blockchain.size());
		System.out.println("=================================================");
		for (int j = 0; j < 10; j++) // Perulangan percobaan
		{
			long tStart = System.currentTimeMillis();
			addBlock(new Block(data, "0", hashMethod, nonce));

			System.out.println("Trying to Mine block 2... ");

			for (int i = 0; i < numberOfBlock; i++) // jumlah block yg digenerate
			{
				addBlock(new Block(data ,blockchain.get(blockchain.size() - 1).hash, hashMethod, nonce));
			}
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			totalTime += elapsedSeconds;System.out.println(elapsedSeconds);
		}
		System.out.println("Average");
		System.out.println(totalTime/10.0);
		System.out.println("\n");
	}
}
