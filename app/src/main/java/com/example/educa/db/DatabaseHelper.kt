package com.example.educa.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "app_database.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Creazione tabella Users
        db.execSQL("""
            CREATE TABLE Users (
                alias TEXT PRIMARY KEY,
                age INTEGER NOT NULL,
                first_name TEXT,
                last_name TEXT,
                tax_code TEXT
            )
        """.trimIndent())

        // Creazione tabella Operators
        db.execSQL("""
            CREATE TABLE Operators (
                alias TEXT PRIMARY KEY,
                first_name TEXT,
                last_name TEXT,
                role TEXT NOT NULL
            )
        """.trimIndent())

        // Creazione tabella Activities
        db.execSQL("""
            CREATE TABLE Activities (
                name TEXT PRIMARY KEY,
                age INTEGER NOT NULL,
                is_favorite BOOLEAN DEFAULT FALSE,
                is_for_group BOOLEAN DEFAULT FALSE,
                is_for_single BOOLEAN DEFAULT FALSE,
                description_short TEXT,
                description_long TEXT,
                duration INTEGER,
                variants TEXT,
                how_to_do_it TEXT
            )
        """.trimIndent())

        // Creazione tabella Objectives
        db.execSQL("""
            CREATE TABLE Objectives (
                name TEXT PRIMARY KEY
            )
        """.trimIndent())

        // Creazione tabella Tools
        db.execSQL("""
            CREATE TABLE Tools (
                name TEXT PRIMARY KEY
            )
        """.trimIndent())

        // Creazione tabella Activity_Tools
        db.execSQL("""
            CREATE TABLE Activity_Tools (
                activity_name TEXT,
                tool_name TEXT,
                FOREIGN KEY (activity_name) REFERENCES Activities(name) ON DELETE CASCADE,
                FOREIGN KEY (tool_name) REFERENCES Tools(name) ON DELETE CASCADE,
                PRIMARY KEY (activity_name, tool_name)
            )
        """.trimIndent())

        // Creazione tabella Activity_Objectives
        db.execSQL("""
            CREATE TABLE Activity_Objectives (
                activity_name TEXT,
                objective_name TEXT,
                FOREIGN KEY (activity_name) REFERENCES Activities(name) ON DELETE CASCADE,
                FOREIGN KEY (objective_name) REFERENCES Objectives(name) ON DELETE CASCADE,
                PRIMARY KEY (activity_name, objective_name)
            )
        """.trimIndent())

        // Creazione tabella User_Objectives
        db.execSQL("""
            CREATE TABLE User_Objectives (
                user_alias TEXT,
                objective_name TEXT,
                FOREIGN KEY (user_alias) REFERENCES Users(alias) ON DELETE CASCADE,
                FOREIGN KEY (objective_name) REFERENCES Objectives(name) ON DELETE CASCADE,
                PRIMARY KEY (user_alias, objective_name)
            )
        """.trimIndent())

        // Creazione tabella Preferred_Activities
        db.execSQL("""
            CREATE TABLE Preferred_Activities (
                operator_alias TEXT,
                activity_name TEXT,
                FOREIGN KEY (operator_alias) REFERENCES Operators(alias) ON DELETE CASCADE,
                FOREIGN KEY (activity_name) REFERENCES Activities(name) ON DELETE CASCADE,
                PRIMARY KEY (operator_alias, activity_name)
            )
        """.trimIndent())

        // Creazione tabella User_Activities
        db.execSQL("""
            CREATE TABLE User_Activities (
                user_alias TEXT,
                activity_name TEXT,
                FOREIGN KEY (user_alias) REFERENCES Users(alias) ON DELETE CASCADE,
                FOREIGN KEY (activity_name) REFERENCES Activities(name) ON DELETE CASCADE,
                PRIMARY KEY (user_alias, activity_name)
            )
        """.trimIndent())
        db.execSQL(
            """
        INSERT INTO Activities (name, age, is_favorite, is_for_group, is_for_single, description_short, description_long, duration)
        VALUES 
            ("Cambi di direzione", 2, 0, 1, 1, "Favorire l'orientamento spaziale, l'attenzione e la flessibilità", "Far disporre i bambini in cerchio e invitarli a correre (in caso di gioco individuale semplicemente definire un punto di partenza e dare il via alla corsa). Quando l'operatore lo richiede, cambiare il senso di marcia.", 7),
            ("I cavalli", 2, 0, 1, 1, "Favorire l'imitazione, la coordinazione ed il ritmo", "Giocare a far finta di essere cavalli e richiedere il passo, il trotto, il galoppo ed il salto ad ostacoli su imitazione. Al termine dell'attività si potrà proporre ai bambini più grandi di disegnare quanto fatto. Inserire nel percorso cerchi o altro materiale adatto a costruire ostacoli. Il cavallo deve eseguire anche evoluzioni entrando nel cerchio, passandoci attraverso, facendolo passare dalla testa, scavalcando ostacoli, ecc.", 12),
            ("I pesciolini", 2, 0, 1, 1, "Sviluppare la coordinazione a terra e l'orientamento spaziale", "Creare un grande mare con materiale vario e giocare a fingere di essere dei pesciolini che nuotano, saltano, muovono gambe e braccia. Durante il nuoto dei pesciolini iniziare a lavorare sullo schema crociato proponendo di avanzare contemporaneamente con braccio e gambe opposti. Al termine, si potrà proporre ai bambini più grandi di disegnare l'attività svolta.", 15),
            ("Il corpo a colori", 2, 0, 1, 1, "Fa prendere coscienza dei due emisferi del corpo", "Disegnare sui cartelloni delle semplici sagome della figura umana, dividerle a metà con una linea verticale e invitare il bambino a colorare le due metà usando pennarelli oppure attaccando carta crespa con colori differenti. Con i bambini più grandi prendere spunto dal disegno per far acquisire coscienza delle differenze tra i due emisferi.", 20),
            ("Puzzle", 3, 0, 1, 1, "Aumentare la consapevolezza corporea e la capacità di rappresentare il corpo nella mente e sul foglio", "Tagliare a metà delle figure del viso, del corpo umano o di animali, di personaggi dei cartoni ecc. e chiedere di ricomporle. Si possono mischiare le varie parti e chiedere al bambino di ricomporre le figure riconoscendo le varie parti.", 15),
            ("Due mani due palline", 2, 0, 1, 1, "Favorire la consapevolezza dei due emisferi", "Mettere al bambino una pallina in ogni mano, possibilmente di colore diverso, chiedergli di fare centro in un canestro o in un cerchio con una pallina. Inserire una sequenza temporale richiedendo di tirare una pallina e poi l'altra. Se i bambini sono tanti, contestualmente si lavora sul turno.", 12)
    """.trimIndent()
        )
        db.execSQL(
            """
        INSERT INTO Tools (name) VALUES 
            ('pennarelli'),
            ('carta crespa'),
            ('Forbici'),
            ('colla'),
            ('carta'),
            ('figurine da tagliare'),
            ('palline di plastica');
        """.trimIndent()
        )
        // Popolamento tabella Objectives
        db.execSQL(
            """
        INSERT INTO Objectives (name) VALUES 
            ('orientamento'),
            ('attenzione'),
            ('flessibilità'),
            ('coordinazione'),
            ('consapevolezza'),
            ('ritmo');
        """.trimIndent()
        )
        db.execSQL(
            """
        INSERT INTO Activity_Tools (activity_name, tool_name)
        VALUES 
            ('Il corpo a colori', 'pennarelli'),
            ('Il corpo a colori', 'carta crespa'),
            ('Puzzle', 'Forbici'),
            ('Puzzle', 'colla'),
            ('Puzzle', 'carta'),
            ('Puzzle', 'figurine da tagliare'),
            ('Due mani due palline', 'palline di plastica');
        """.trimIndent()
        )
        // Popolamento tabella Activity_Objectives
        db.execSQL(
            """
        INSERT INTO Activity_Objectives (activity_name, objective_name)
        VALUES 
            ('Cambi di direzione', 'orientamento'),
            ('Cambi di direzione', 'attenzione'),
            ('Cambi di direzione', 'flessibilità'),
            ('I cavalli', 'imitazione'),
            ('I cavalli', 'coordinazione'),
            ('I cavalli', 'ritmo'),
            ('I pesciolini', 'coordinazione'),
            ('I pesciolini', 'orientamento'),
            ('Il corpo a colori', 'consapevolezza'),
            ('Il corpo a colori', 'capacità'),
            ('Puzzle', 'consapevolezza'),
            ('Due mani due palline', 'consapevolezza');
        """.trimIndent()
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Gestisci gli aggiornamenti del database qui
        if (oldVersion < newVersion) {
            // Elimina le tabelle esistenti e ricreale
            db.execSQL("DROP TABLE IF EXISTS Users")
            db.execSQL("DROP TABLE IF EXISTS Operators")
            db.execSQL("DROP TABLE IF EXISTS Activities")
            db.execSQL("DROP TABLE IF EXISTS Objectives")
            db.execSQL("DROP TABLE IF EXISTS Tools")
            db.execSQL("DROP TABLE IF EXISTS Activity_Tools")
            db.execSQL("DROP TABLE IF EXISTS Activity_Objectives")
            db.execSQL("DROP TABLE IF EXISTS User_Objectives")
            db.execSQL("DROP TABLE IF EXISTS Preferred_Activities")
            db.execSQL("DROP TABLE IF EXISTS User_Activities")

            onCreate(db)
    }
}
}
