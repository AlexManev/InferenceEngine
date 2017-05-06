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

Disscuss Outputs:
-------------------------------------------------------------------------------------------------------------

Natural Language Test case will output in Natural Language (eg. YES: i'm happy. i win. you loose.)


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
The GUI works same as its specified on the requirements.
Natural Language works on

    If [variable] then [variable]
    If [variable] and [variable] then [variable]
    If [variable],[variable]
    [variable]
    every rule must end with .
    
You can see examples up in the test cases.

Summary report:
=============================================================================================================

