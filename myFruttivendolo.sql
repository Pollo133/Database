DROP TABLE IF EXISTS frutti;
CREATE TABLE frutti(id INTEGER PRIMARY KEY, nome TEXT, stagione TEXT, prezzoKg INT);
INSERT INTO frutti VALUES(4, "mela renetta", "AUTUNNALE", 150);
INSERT INTO frutti VALUES(5, "carambola", "ESTIVA", 150);
INSERT INTO frutti VALUES(6, "banana cavendish", "ANNUALE", 250);
.mode column
SELECT * FROM frutti;

