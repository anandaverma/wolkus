package com.wolkus.body;

import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateError;

import com.wolkus.brain.Brain;
import com.wolkus.ear.Ear;

public class Body {
	public static void main(String[] args) throws IOException, EngineException, AudioException, IllegalArgumentException, InterruptedException, EngineStateError, PropertyVetoException
    {
            Ear ear = new Ear();
            Brain brain  = new Brain();
            
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
