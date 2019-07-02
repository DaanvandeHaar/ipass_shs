CREATE TABLE Boodschapenlijst_item
(
  boodschappenlijst_item_nummer SERIAL       NOT NULL,
  beschrijving                  varchar(255) NOT NULL,
  bedrag                        float4       NOT NULL,
  datum                         date         NOT NULL,
  gebruiker_nummer              int4         NOT NULL,
  boodschappenlijst_nummer      int4         NOT NULL,
  PRIMARY KEY (boodschappenlijst_item_nummer)
);
CREATE TABLE Booschappenlijst
(
  boodschappenlijst_nummer SERIAL       NOT NULL,
  naam                     varchar(255) NOT NULL,
  beschrijving             varchar(255) NOT NULL,
  totaalbedrag             float8,
  PRIMARY KEY (boodschappenlijst_nummer)
);
CREATE TABLE Booschappenlijst_Gebruiker
(
  boodschappenlijst_nummer int4 NOT NULL,
  gebruiker_nummer         int4 NOT NULL,
  PRIMARY KEY (boodschappenlijst_nummer, gebruiker_nummer)
);
CREATE TABLE Gebruiker
(
  gebruiker_nummer SERIAL                   NOT NULL,
  voornaam         varchar(255)             NOT NULL,
  achternaam       varchar(255)             NOT NULL,
  email            varchar(255)             NOT NULL UNIQUE,
  leeftijd         int4,
  rol              varchar(255) DEFAULT '0' NOT NULL,
  wachtwoord_hash  varchar(255)             NOT NULL,
  PRIMARY KEY (gebruiker_nummer)
);
CREATE TABLE Gebruiker_Huistaak
(
  gebruiker_nummer        int4 NOT NULL,
  huistaakHuistaak_nummer int4 NOT NULL,
  PRIMARY KEY (gebruiker_nummer, huistaakHuistaak_nummer)
);
CREATE TABLE Huistaak
(
  huistaak_nummer SERIAL NOT NULL,
  begin_datum     date   NOT NULL UNIQUE,
  eind_datum      date,
  totaal_bedrag   float4,
  totaal_items    int4   NOT NULL,
  PRIMARY KEY (huistaak_nummer)
);
CREATE TABLE Huistaak_afspraak
(
  huistaak_afspraak_nummer SERIAL NOT NULL,
  datum_doen               date,
  datum_gedaan             date,
  gebruiker_nummer         int4   NOT NULL,
  huistaak_nummer          int4   NOT NULL,
  PRIMARY KEY (huistaak_afspraak_nummer)
);
CREATE TABLE Rekeing
(
  Bedrag                   float8,
  Rekening_nummer          SERIAL NOT NULL,
  gebruiker_nummer         int4   NOT NULL,
  boodschappenlijst_nummer int4   NOT NULL,
  PRIMARY KEY (Rekening_nummer)
);
CREATE UNIQUE INDEX Gebruiker_gebruiker_nummer ON Gebruiker (gebruiker_nummer);
CREATE UNIQUE INDEX Rekeing_Rekening_nummer ON Rekeing (Rekening_nummer);
ALTER TABLE Huistaak_afspraak
  ADD CONSTRAINT FKHuistaak_a189152 FOREIGN KEY (gebruiker_nummer) REFERENCES Gebruiker (gebruiker_nummer);
ALTER TABLE Boodschapenlijst_item
  ADD CONSTRAINT FKBoodschape493157 FOREIGN KEY (gebruiker_nummer) REFERENCES Gebruiker (gebruiker_nummer);
ALTER TABLE Huistaak_afspraak
  ADD CONSTRAINT FKHuistaak_a155774 FOREIGN KEY (huistaak_nummer) REFERENCES Huistaak (huistaak_nummer);
ALTER TABLE Boodschapenlijst_item
  ADD CONSTRAINT FKBoodschape695819 FOREIGN KEY (boodschappenlijst_nummer) REFERENCES Booschappenlijst (boodschappenlijst_nummer);
ALTER TABLE Booschappenlijst_Gebruiker
  ADD CONSTRAINT FKBooschappe692670 FOREIGN KEY (boodschappenlijst_nummer) REFERENCES Booschappenlijst (boodschappenlijst_nummer);
ALTER TABLE Booschappenlijst_Gebruiker
  ADD CONSTRAINT FKBooschappe496306 FOREIGN KEY (gebruiker_nummer) REFERENCES Gebruiker (gebruiker_nummer);
ALTER TABLE Gebruiker_Huistaak
  ADD CONSTRAINT FKGebruiker_399501 FOREIGN KEY (gebruiker_nummer) REFERENCES Gebruiker (gebruiker_nummer);
ALTER TABLE Gebruiker_Huistaak
  ADD CONSTRAINT FKGebruiker_471895 FOREIGN KEY (huistaakHuistaak_nummer) REFERENCES Huistaak (huistaak_nummer);
ALTER TABLE Rekeing
  ADD CONSTRAINT FKRekeing826109 FOREIGN KEY (gebruiker_nummer) REFERENCES Gebruiker (gebruiker_nummer);
ALTER TABLE Rekeing
  ADD CONSTRAINT FKRekeing956503 FOREIGN KEY (boodschappenlijst_nummer) REFERENCES Booschappenlijst (boodschappenlijst_nummer);