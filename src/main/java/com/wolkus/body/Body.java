package com.wolkus.body;

import java.io.IOException;

import com.wolkus.brain.Brain;
import com.wolkus.ear.Ear;
import com.wolkus.mouth.Mouth;

public class Body {
	public static void main(String[] args) throws IOException
    {
            Mouth mouth = new Mouth();
            Ear ear = new Ear();
            Brain brain  = new Brain();

            mouth.start();
            ear.start();
            brain.start();

            while (true) {
                    try {
                            Thread.sleep(50);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
            }
    }
}
