# InferenceEngine
Propositional Logic Inference Engine, working with defined format and natural language.

Student Details:
-------------------------------------------------------------------------------------------------------------
Aleksandar Manev	100586500
Cameron Watts   	

Group Number: 			


Features / Bugs / Missings
=============================================================================================================
- **Truth Table**:

- **Forward Chaining**:

- **Backwards Chaining**:

- **Natural Language**:

Test cases: 
=============================================================================================================
    TELL
    a=>b; b=>c; b&c=>a;
    ASK
    a

    TELL
    p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
    ASK
    d

    TELL
    if I'm happy then I win. If I win then You Loose. if you play then you have fun. If I have fun and you have fun then It's a good day. If It's a good day and I dance then you dance. If you loose, you are broke. If you loose and I win then you play. You have fun. I'm millionaire. I have fun. I'm happy.
    ASK
    You Loose.
    
    TELL
    p=>q; l&m=>p;b&l=>m;a&p=>l;a&b=>l;a;b;
    ASK
    l
    
    

Disscuss Outputs:
-------------------------------------------------------------------------------------------------------------

Natural Language Test case will output in Natural Language (eg. YES: i'm happy. i win. you loose.)

Research
=============================================================================================================
For our research, we did Natural Language Processing. Our strategy was to detect patterns in a sentence and try to convert it into variables and symbols. Using Regular expression we were able to manipulate with the sentence string and identify the key values. We compare them and construct Horn Clause rules of propositional logic.

Our Approach:
-------------------------------------------------------------------------------------------------------------
We firstly defined all the rules that one sentence must or could have.

Must Have                           | Example       | Translated As
----------------------------------- | ------------- | --------------
finish rule with a full-stop.       | .             | ;
Variable                            | I am happy    | a

Could Have                          | Example       | Translated As
----------------------------------- | ------------- | --------------
Conjunction                         | and           | &
Implementation                      | then or ","   | =>
If (ignored)                        | If I am happy | I am happy

Using Regular expressing,
then([\s\S]*) - select everything after "then"
,([\\s\\S]*)  - select everything after ","
^(.*?)then    - select everything before "then"
^(.*?),       - select everything before ","

We were able to split the rule sentence into two sides, left and right side of the implementation. Form there we split each side on "and" and each one of the values seperated by "and" vas representing a variable.

Example:
------------------------------------------------------------------------------------------------------------
If I am happy, I am winning. If I am winning and I am dancing then you are winning. I am happy.
Translation: a=>b;b&c=>d;a;

Having this translated KB I can use it my TT, BC, FC algorithms.


Output:
------------------------------------------------------------------------------------------------------------
The output will be printed in Natural Language.

Acknowledgements/Resources: 
=============================================================================================================
We used the content from the Chapter 7 of the book “Artificial Intelligence, 
A modern Approach III Edition” by Stuart J Russell and Peter Norvig to 
understand better how the the Forward Chaining  and Backward Chaining
algorithms work.

http://stackoverflow.com/questions/18413204/regular-expression-to-select-everything-before-and-up-to-a-particular-text
http://stackoverflow.com/questions/10813154/converting-number-to-letter
https://people.cs.pitt.edu/~milos/courses/cs2740/Lectures/class6.pdf
http://stackoverflow.com/questions/10723168/generating-truth-tables-in-java

Notes: 
=============================================================================================================
The UI works same as its specified on the requirements.

Natural Language works on Horn Clause type of kb

    If [variable] then [variable].
    If [variable] and [variable] then [variable].
    If [variable],[variable].
    [variable].
    every rule must end with .
    
You can see examples up in the test cases.

Summary report:
=============================================================================================================
We had 5 tasks to complete in total:
1. Truth Tabe - Aleksandar Manev
2. Forward Chaining - Cameron Watts
3. Backwards Chaining - Aleksandar Manev
4. Research - Aleksandar Manev
5. Documentation - Cameron Watts
Alex developed 60% of the assigment. Due to he's more free scadule he was confident to do the Trurh table, Backwards chaining and the Research part of the assigment. Aleks's development went smoothly being able to finish both of the algorithms for TT and BC in the first two days of the assigment period. This gave him time to work on the Reasearch and finish it by the end of the week. Aleks's found some bugs in the BC implementation and he re-build the algorythm to fix this issue. It took him 1 day to finish finalize the BC algorithm.
Cameron developed 40% of the assigment. He worked on the forward chaining algorithm and documentation of the code. His development went smooth with being able to finish his algorythm in the first week of the assigment period. There was only one bug that we fixed it later on.