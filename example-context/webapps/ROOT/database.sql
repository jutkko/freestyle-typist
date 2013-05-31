DROP TABLE Typer;

CREATE TABLE Typer (
  UserID text unique not null,
  UserPassword text not null
);

INSERT INTO Typer VALUES('Jun', 'ilikepink');
