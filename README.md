# Auto-Completion
A program that, given a string, prints the top n closest words in descending order of weight, along with their weight. It takes a wikipedia of words (in a formatted text file) as an argument, and the value n as an argument. 

# Before Use
Please note that this code uses a library of methods (seen imported at the top of the files). 
Please follow this link(https://algs4.cs.princeton.edu/code/) to download the correct path to this library so that you may be able to run and use this code correctly. 

# To use
1. Download all the files in this repo
2. Compile: 
    - "javac-algs4 Autocomplete.java"
3. Now to run, we need to input "wiktionary.txt" where the program is learning the weights and receives a large glossary of words. We also need to input the number of words ("n") that the program needs to look for that are closest to the input word that we will provide. Once we run this command, we will be in standard input mode, where the computer will now be receiving words, and after sending the some string followed by a newline ("\n"), our program will look for that string and output the most relevant words. To run: 
    - "java-algs4 Autocomplete wiktionary.txt n"
    - You will then be taken to std input to enter a string
    - type in "auto" then hit ENTER
    - you should get output that says the following:
        - "2 matches\n619695 automobile\n424997 automatic"
        - the number is the weight associated with the word
    - you are then prompted to enter more strings!
    - hit CRTL-C to end the program
    
 You now have an autocomplete engine!
    
        
    
 
    
