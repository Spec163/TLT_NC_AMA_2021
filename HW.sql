/* ������� 6 */
INSERT INTO Student
(School_id, NAME, AGE) 
VALUES (1, 'Ivan', 19);


/* ������� 5 */
DELETE FROM Student
 WHERE name = 'Maksim';

/* ������� 4 */
SELECT Name FROM student
WHERE EMPNO <= 23 and EMPNO >= 21 AND NAME != 'Anton';

/* ������� 7 */
UPDATE Student SET Age = Age + 1
WHERE Age > 20;

/* ������� 1 */
INSERT INTO Peopl
(School_id, NAME, AGE) 
VALUES     (2, 'Kate', 20), 
            (2, 'Dima', 20),
            (2, 'Slava', 20);
            


