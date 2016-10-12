# pa165-activity-tracker

System bude evidovat seznam uzivatelu a sportovnich aktivit, ktere uzivatel muze provozovat. Kazda sportovni aktivita vyzaduje ruzne energeticke vydaje. Ty zalezi na vice udajich, zejmena na vaze jedince, veku i pohlavi. Tabulky energetickych vydaju lze nalezt na internetu (napr. http://www.nutristrategy.com/activitylist.htm). System bude zaznamenavat kazdou aktivitu, cas kdy byla provozovana, dobu trvani, spalene kalorie a delku trasy (v pripade ze se jedna o aktivitu, kde se delka trasy da merit - beh, jizda na kole...) 

V systemu budou 4 entity:
* uzivatel (vek, vaha, jmeno, pohlavi...)
* sportovni aktivita (nazev...)
* spalene kalorie behem aktivit (viz napr. http://www.nutristrategy.com/activitylist.htm)
* zaznam sportovni aktivity (cas, vzdalenost, doba trvani...)
