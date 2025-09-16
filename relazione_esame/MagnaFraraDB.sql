-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: MagnaFraraDB
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `id_recensione` int NOT NULL AUTO_INCREMENT,
  `testo` varchar(255) DEFAULT NULL,
  `titolo` varchar(255) DEFAULT NULL,
  `valutazione` int DEFAULT NULL,
  `data_pubb` datetime(6) DEFAULT NULL,
  `id_ristorante` bigint NOT NULL,
  `id_utente` bigint NOT NULL,
  PRIMARY KEY (`id_recensione`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (45,'Piatti ricercati e servizio impeccabile. Sicuramente tornerò.','Esperienza fantastica',5,'2025-05-12 00:00:00.000000',1,1),(46,'Qualità ottima ma i prezzi sono esagerati.','Troppo caro',3,'2025-05-20 00:00:00.000000',1,1),(47,'Ambiente elegante e piatti molto curati, consiglio il menù degustazione.','Da provare',4,'2025-06-01 00:00:00.000000',1,1),(48,'Pasta fresca buonissima, sembra fatta dalla nonna.','Come a casa',5,'2025-05-15 00:00:00.000000',1,1),(49,'Cibo ottimo ma il locale era troppo affollato.','Un po\' rumoroso',4,'2025-06-02 00:00:00.000000',1,1),(50,'Il pesce era davvero di qualità, consigliato.','Sushi freschissimo',5,'2025-05-18 00:00:00.000000',1,1),(51,'Abbiamo aspettato 40 minuti per i piatti.','Servizio lento',2,'2025-05-25 00:00:00.000000',1,1),(52,'Atmosfera rilassante e personale gentile.','Locale accogliente',4,'2025-06-03 00:00:00.000000',1,1),(53,'La carta dei vini è eccezionale, piatti raffinati.','Ottimo vino',5,'2025-05-22 00:00:00.000000',2,2),(54,'Molto buono ma porzioni ridotte per il prezzo.','Porzioni piccole',3,'2025-05-30 00:00:00.000000',2,2),(55,'Il miglior burger mai provato, ingredienti freschi.','Hamburger top',5,'2025-05-19 00:00:00.000000',2,2),(56,'Gli hamburger ottimi ma le patatine erano fredde.','Patatine fredde',2,'2025-05-28 00:00:00.000000',2,2),(57,'Il curry era piccante al punto giusto, ottimo naan.','Spezie autentiche',5,'2025-05-21 00:00:00.000000',2,2),(58,'Non specificano il livello di piccantezza, difficile da mangiare.','Troppo piccante',2,'2025-05-29 00:00:00.000000',2,2),(59,'Hanno spiegato tutti i piatti, ottimo servizio.','Personale gentile',4,'2025-06-01 00:00:00.000000',2,2),(60,'Perfetto per una serata tra amici, ottima sangria.','Tapas buonissime',5,'2025-05-27 00:00:00.000000',3,3),(61,'Cibo buono ma troppa confusione nel locale.','Troppo affollato',3,'2025-06-04 00:00:00.000000',3,3),(62,'Impasto soffice e leggero, come a Napoli.','Pizza autentica',5,'2025-05-16 00:00:00.000000',3,3),(63,'La pizza era buona ma un po’ troppo salata.','Troppo salata',3,'2025-05-23 00:00:00.000000',3,3),(64,'Il sapore della pizza è unico, davvero ottimo.','Forno a legna spettacolare',5,'2025-06-05 00:00:00.000000',3,3),(65,'Piatti freschi e salutari, perfetto per l’estate.','Cucina leggera',4,'2025-05-26 00:00:00.000000',3,3),(66,'Porzioni piccole e conto salato.','Troppo caro',2,'2025-06-02 00:00:00.000000',3,3),(67,'Locale accogliente e spazioso, personale estremamente rude','La mia visita',1,'2025-09-06 12:12:36.746945',1,2),(68,'Buonissimo e molto economico','Ottima serata',5,'2025-09-06 14:18:29.307266',2,2),(69,'Perfetto per portare la propria fidanzata ad una cena romantica','La mia visita',5,'2025-09-06 14:44:12.735890',7,2),(70,'Un po\' sporco','Mal di pancia',3,'2025-09-06 14:53:34.465900',15,2),(71,'Sono stato male','Non bene',2,'2025-09-06 15:09:46.758448',9,2),(72,'Un mio amico stava venendo a cena ed è stato investito da un autobus. Posto orribile','NON VENITE',1,'2025-09-06 18:22:12.498623',10,2),(73,'Era tutto buonissimo, personale cordiale, bellissima serata.','Spaziale',1,'2025-09-06 18:46:17.589472',6,2),(74,'Molto molto male','La mia visita',1,'2025-09-16 09:48:57.918853',5,2),(75,'Personale gentile e servizio veloce. Cibo buono ma non buonissimo','Accogliente',4,'2025-09-16 11:25:26.151496',15,24),(76,'Passando da fuori escono dei buoni odori però','Non ci sono mai stato',5,'2025-09-16 11:26:13.880419',6,24),(77,'Mi aspettavo più buteghe','Cusina e Butega',4,'2025-09-16 11:26:47.849786',14,24),(78,'Ho chiesto anche in cucina ma mi hanno detto che era deceduto. Un gran peccato','Non ho visto il Savonarola',3,'2025-09-16 11:27:25.523606',7,24),(79,'Fanno un\'oca pazzesca, mi sarebbe piaciuto anche un po\' di sushi però','Oca molto buona',4,'2025-09-16 11:27:59.446556',13,24),(80,'Effettivamente è proprio in centro. L\'unica sua qualità','Centralissimo',2,'2025-09-16 11:28:23.205057',4,24),(81,'una costata spaziale','Manzo delizioso',5,'2025-09-16 11:28:54.491637',12,24),(82,'Sono andato in bagno e la mia borsa era sparita, così come i camerieri e i proprietari.','Mi hanno scippato',1,'2025-09-16 11:29:32.612364',11,24),(83,'Non ho abbastanza dita per contare quante volte il cameriere mi ha starnutito addosso. Il ragù di cinghiale era molto buono','Sporcizia e maleducazione',1,'2025-09-16 11:31:00.647448',5,24),(84,'Cibo passabile, nemmeno un trattore','Pochi trattori',2,'2025-09-16 11:31:36.941325',9,24),(85,'Non sono nemmeno stato male la mattina seguente','ottimo',5,'2025-09-16 11:32:08.188495',8,24),(86,'Finalmente ho assaggiato la pasta gamberetti, salsiccia e provola. Unico','Incredibile',5,'2025-09-16 11:32:42.456367',16,24),(87,'Bel posto, bella gente','Ottimo',5,'2025-09-16 11:34:35.429535',14,2),(88,'Tutto perfetto, i bagni un po\' sporchi','Cordialità e simpatia',3,'2025-09-16 11:35:17.900687',13,2),(89,'Luca ha cercato di farmi del male, ho avuto molta paura','Luca',4,'2025-09-16 11:35:49.117624',4,2),(90,'Mi hanno detto di brucare in giardino','Poche opzioni per vegetariani',2,'2025-09-16 11:36:39.178803',12,2),(91,'Non ascoltate gli altri, sono solo invidiosi','Buonissimo',5,'2025-09-16 11:37:05.915624',11,2),(92,'L\'immaginazione è l\'unico limite. E il portafogli','Magnifico',4,'2025-09-16 11:44:59.830385',16,2),(93,'Nulla da dire, molto buono ed economico','Buono',5,'2025-09-16 11:46:05.351883',10,2);
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ristorante`
--

DROP TABLE IF EXISTS `ristorante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ristorante` (
  `id_ristorante` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `descrizione` varchar(2000) DEFAULT NULL,
  `tipo_cucina` varchar(255) DEFAULT NULL,
  `menu_img` varchar(255) NOT NULL,
  `rist_img` varchar(255) NOT NULL,
  `id_gestore` bigint NOT NULL,
  PRIMARY KEY (`id_ristorante`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ristorante`
--

LOCK TABLES `ristorante` WRITE;
/*!40000 ALTER TABLE `ristorante` DISABLE KEYS */;
INSERT INTO `ristorante` VALUES (1,'I Piaceri di Lucrezia','Verace brasserie che offre ottimo cibo del territorio rispettando la tradizione e strizzando un occhio alle eccellenze della biosfera nazionale. È sempre presente una vasta scelta di polente così come i funghi porcini ed i tartufi sono sempre presenti nel menù. Ottimamente recensite le bistecche alla fiorentina e la pasta fresca rigorosamente \"tirata\" a mattarello. È inserito nel circuito \"Ristorante Tipico Ferrarese\".','Brasserie, Ferrarese','ipiaceridilucrezia_menu.jpg','ipiaceridilucrezia.jpg',1),(2,'Trattoria Il Mandolino','Il Ristorante Trattoria \"Il Mandolino\" é il locale tipico ferrarese per clienti alla ricerca di prodotti autentici ma con occhio attento alla spesa da sostenere. Le nostre proposte di cucina mirano a soddisfare la curiosità verso piatti dal gusto eccellente e dalla preparazione curata: pasta all\'uovo fatta a mano con i ripieni di zucca o ricotta; salami di produzione locale conosciuti solo in zona ma propagandati su tutte le pubblicazioni, come ad esempio la salama da sugo; dolci tradizionali fatti in casa, come la torta tenerina al cioccolato. Tutto accompagnato da vini di produzione locale.','Ferrarese','trattoriailmandolino_menu.jpg','trattoriailmandolino.jpg',1),(3,'Trattoria da Noemi','Cucina tipica ferrarese, pasta fatta a mano con mattarello, dolci fatti in casa ','Emliana, Ferrarese','trattoriadanoemi_menu.jpg','trattoriadanoemi.jpg',3),(4,'Molto più che Centrale','Molto più che centrale is an Italian restaurant situated in the Heart of Ferrara. Nearly the Cathedral, here you Can eat traditional ferrarese food, but also revisited places. Specialised in \"game and game\" kitchen.\n','Italiana','moltopiuchecentrale_menu.jpg','moltopiuchecentrale.jpg',3),(5,'Osteria Strabassotti','Via Fondobanchetto 2/a, 44121, Ferrara Italia','Italiana, Ferrarese, Emiliana','osteriastrabassotti_menu.jpg','osteriastrabassotti.jpg',3),(6,'Cucina Bacilieri','Cucina Bacilieri nasce dall’evoluzione di un progetto di ricerca e degustazione gastronomica che unisce la cucina della tradizione emiliana e ferrarese alle idee di cucina alternativa dello chef Michele Bacilieri a Ferrara.\nL’atmosfera intima profuma della passione dello chef e della sua brigata in un ambiente di 10 tavoli riservati e accoglienti, dove assaporare con tutti i sensi.\n\n','Carne, Pesce','cucinabacilieri_menu.jpg','cucinabacilieri.jpg',3),(7,'Hostaria Savonarola','L’hostaria Savonarola si trova nel centro di Ferrara, fra il Castello Estense e il Palazzo Ducale, sotto il loggiato che dà sulla piazza con l’omonima statua dedicata appunto al frate ferrarese Girolamo Savonarola. Ho scovato questa osteria facendo una ricerca online su dove mangiare il vero pasticcio ferrarese. Diversi siti turistici locali la indicano come uno dei posti più tipici dove provare i piatti della tradizione di Ferrara. L’Hostaria Savonarola è citata anche sulla rinomata guida di Gambero Rosso, per i suoi cappellacci di zucca.','Ferrarese','hostariasavonarola_menu.jpg','hostariasavonarola.jpg',3),(8,'Trattoria Le Nuvole','La trattoria, nel nostro immaginario di bambini  è sempre stata il posto speciale dove la famiglia andava la domenica a pranzo, a mangiare cose buone: la pasta all’uovo che la “rzdora” tirava con il mattarello, il fritto di calamaretti, i dolci fatti in casa. La trattoria era un posto dove già all’ingresso ti accoglieva un buon profumo di cibo, dove entravi accolto dalla familiarità del proprietario che con un sorriso ti faceva sedere al “tuo” tavolo, ti illustrava i piatti del giorno e ti versava il vino della casa.','Pesce','trattorialenuvole_menu.jpg','trattorialenuvole.jpg',3),(9,'Trattoria la Ferrarese','Ciao a tutti. Siamo Sara (Oste) e Mattia (Chef), titolari di questa piccola trattoria dal 2010. Ci troviamo alle porte di Ferrara, vicini al percorso cicloturistico del fiume Po e a pochi chilometri dal casello dell\'autostrada Ferrara nord sull\' A13. Siamo raggiungibili anche con la linea 11 dell’autobus urbano.\n\nOffriamo una cucina di \"una volta\" in chiave moderna, creando piatti con prodotti del territorio e soprattutto cercando di seguire la stagionalità. La pasta e i dolci sono di nostra produzione. Oltre al menù alla carta proponiamo anche piatti del giorno.\n\nLa cantina segue la nostra cucina ed è legata prevalentemente al territorio.\n\nC’è possibilità di parcheggio e le sale sono climatizzate. Nei mesi primaverili ed estivi solitamente allestiamo un dehor per godere anche dell\'ambiente esterno.','Ferrarese','trattorialaferrarese_menu.jpg','trattorialaferrarese.jpg',3),(10,'Quel fantastico Giovedì','Piatti regionali italiani presentati in modo artistico in un elegante locale con arte astratta e tavoli in cortile.\n','Ferrarese, Emiliana','quelfantasticogiovedi_menu.jpg','quelfantasticogiovedi.jpg',3),(11,'Osteria la Compagna','Situata nel cuore di Ferrara, l\'Osteria è ricavata in un antico cassero ferrarese tutto in legno e pietra. L’ambiente è caldo ed accogliente, arredato con cose semplici nel rispetto delle antiche tradizioni, è presente inoltre una piccola distesa esterna.\nCucina Tipica Ferrarese e Cucina Mediterranea di Terra e di Mare. Tutte le paste sono fatte in casa, con un particolare riguardo per i Cappellacci ripieni di zucca violina. I nostri Clienti apprezzano la nostra cucina tradizionale genuina, l\'accoglienza affabile e garbata, il costo contenuto in rapporto alla qualità e, quando capita, l\'intrattenimento a fine cena.\nIl locale non è provvisto degli spazi e delle attrezzature adatte ai bambini.    \n','Emiliana, Ferrarese','osterialacompagnia_menu.jpg','osterialacompagnia.jpg',3),(12,'Osteria il Frantoio','Via Dei Baluardi 51, 44121, Ferrara Italia','Emiliana, Ferrarese','osteriailfrantoio_menu.jpg','osteriailfrantoio.jpg',3),(13,'L\'Oca Giuliva','La fusione tra la semplicità degli ingredienti e la creatività dello chef illumina una continua ricerca culinaria che inizia alle prime luci dell’alba, con la spesa al mercato rionale. Materia prima di stagione e prodotti selezionati a km zero sono gli ingredienti principali per piatti genuini e di altissima qualità.\n\nIl menù viene rinnovato ogni due mesi, selezionando accuratamente portate che variano a seconda dell’offerta stagionale.\n\n','Italiana','locagiuliva_menu.jpg','locagiuliva.jpg',3),(14,'Cusina e Butega','Da Cusina e Butega rivivono le emozioni intense come l\'amicizia e la gioia dello \"stare a tavola\". La riscoperta dei sapori antichi in un\'atmosfera allo stesso tempo elegante e conviviale è resa concreta dalla professionalità di oltre 30 anni di esperienza nell\'arte della cucina.','Italiana, Ferrarese','cusinaebutega_menu.jpg','cusinaebutega.jpg',3),(15,'Ca d\'Frara','Attraverso i nostri piatti, vogliamo raccontarvi di un territorio fatto di terra e acqua, e di una cucina tradizionale,che affonda le sue radici nella storia della città, e nella sua cultura popolare.\nLe specialità più autentiche, quelle che il tempo non ha cambiato, la passione e la dedizione a questo mestiere, sono alla base del nostro concetto di “fare ristorazione”.\n','Emiliana, Ferrarese','cadfrara_menu.jpg','cadfrara.jpg',3),(16,'YourPasta','Crea il tuo piatto perfetto scegliendo tra pasta fresca, condimenti deliziosi, proteine di qualità e topping esclusivi.','Italiana, Emiliana','yourpasta_menu.jpg','yourpasta.jpg',1);
/*!40000 ALTER TABLE `ristorante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id_utente` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `data_reg` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gestore` bit(1) NOT NULL,
  PRIMARY KEY (`id_utente`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Mario','Rossi','mario.rossi@email.com','password123','2025-08-29 13:26:54',_binary ''),(2,'Elisa','Neri','elisa.neri@email.com','elisa123','2025-08-29 14:01:11',_binary '\0'),(3,'Camilla','Bellantuono','camilbellantuono@gmail.com','cami123','2025-08-30 10:52:10',_binary ''),(24,'Luca','Rossi','luca.rossi@example.com','pwdLuca1','2025-09-05 20:55:10',_binary '\0'),(25,'Marco','Bianchi','marco.bianchi@example.com','pwdMarco2','2025-09-05 20:55:10',_binary '\0'),(26,'Giulia','Verdi','giulia.verdi@example.com','pwdGiulia3','2025-09-05 20:55:10',_binary '\0'),(27,'Francesca','Neri','francesca.neri@example.com','pwdFra4','2025-09-05 20:55:10',_binary '\0'),(28,'Andrea','Russo','andrea.russo@example.com','pwdAndre5','2025-09-05 20:55:10',_binary '\0'),(29,'Chiara','Conti','chiara.conti@example.com','pwdChiara6','2025-09-05 20:55:10',_binary '\0'),(30,'Matteo','Greco','matteo.greco@example.com','pwdMatteo7','2025-09-05 20:55:10',_binary '\0'),(31,'Sara','Marino','sara.marino@example.com','pwdSara8','2025-09-05 20:55:10',_binary '\0'),(32,'Davide','Fontana','davide.fontana@example.com','pwdDavide9','2025-09-05 20:55:10',_binary '\0'),(33,'Elisa','Ferrari','elisa.ferrari@example.com','pwdElisa10','2025-09-05 20:55:10',_binary '\0'),(34,'Stefano','Lombardi','stefano.lombardi@example.com','pwdStef11','2025-09-05 20:55:10',_binary '\0'),(35,'Marta','Moretti','marta.moretti@example.com','pwdMarta12','2025-09-05 20:55:10',_binary '\0'),(36,'Simone','Rinaldi','simone.rinaldi@example.com','pwdSimo13','2025-09-05 20:55:10',_binary '\0'),(37,'Valentina','De Luca','valentina.deluca@example.com','pwdVale14','2025-09-05 20:55:10',_binary '\0'),(38,'Alessandro','Testa','alessandro.testa@example.com','pwdAlex15','2025-09-05 20:55:10',_binary '\0'),(39,'Ilaria','Galli','ilaria.galli@example.com','pwdIla16','2025-09-05 20:55:10',_binary '\0'),(40,'Giovanni','Caruso','giovanni.caruso@example.com','pwdGio17','2025-09-05 20:55:10',_binary '\0'),(41,'Federica','Ricci','federica.ricci@example.com','pwdFede18','2025-09-05 20:55:10',_binary '\0'),(42,'Paolo','Martini','paolo.martini@example.com','pwdPaolo19','2025-09-05 20:55:10',_binary '\0'),(43,'Laura','Costa','laura.costa@example.com','pwdLaura20','2025-09-05 20:55:10',_binary '\0'),(44,'Nicola','Romano','nicola.romano@example.com','pwdNicola21','2025-09-05 20:56:39',_binary ''),(45,'Silvia','Esposito','silvia.esposito@example.com','pwdSilvia22','2025-09-05 20:56:39',_binary ''),(46,'Fabio','De Angelis','fabio.deangelis@example.com','pwdFabio23','2025-09-05 20:56:39',_binary ''),(47,'Elena','Grassi','elena.grassi@example.com','pwdElena24','2025-09-05 20:56:39',_binary ''),(48,'Lorenzo','Pellegrini','lorenzo.pellegrini@example.com','pwdLorenzo25','2025-09-05 20:56:39',_binary ''),(49,'Claudia','Fiore','claudia.fiore@example.com','pwdClaudia26','2025-09-05 20:56:39',_binary ''),(50,'Riccardo','Santoro','riccardo.santoro@example.com','pwdRicky27','2025-09-05 20:56:39',_binary ''),(51,'Angela','Riva','angela.riva@example.com','pwdAngela28','2025-09-05 20:56:39',_binary ''),(52,'Tommaso','Ferretti','tommaso.ferretti@example.com','pwdTommy29','2025-09-05 20:56:39',_binary ''),(53,'Martina','Gentile','martina.gentile@example.com','pwdMarti30','2025-09-05 20:56:39',_binary ''),(54,'Giorgio','Palmieri','giorgio.palmieri@example.com','pwdGiorgio31','2025-09-05 20:56:39',_binary ''),(55,'Beatrice','Serra','beatrice.serra@example.com','pwdBea32','2025-09-05 20:56:39',_binary ''),(56,'Roberto','Giordano','roberto.giordano@example.com','pwdRobe33','2025-09-05 20:56:39',_binary ''),(57,'Alessia','Parisi','alessia.parisi@example.com','pwdAle34','2025-09-05 20:56:39',_binary ''),(58,'Daniele','Monti','daniele.monti@example.com','pwdDani35','2025-09-05 20:56:39',_binary ''),(59,'Caterina','De Rosa','caterina.derosa@example.com','pwdCate36','2025-09-05 20:56:39',_binary ''),(60,'Vincenzo','Barbieri','vincenzo.barbieri@example.com','pwdVince37','2025-09-05 20:56:39',_binary ''),(61,'Serena','Marchetti','serena.marchetti@example.com','pwdSere38','2025-09-05 20:56:39',_binary ''),(62,'Pietro','Longo','pietro.longo@example.com','pwdPietro39','2025-09-05 20:56:39',_binary ''),(63,'Alice','Caputo','alice.caputo@example.com','pwdAlice40','2025-09-05 20:56:39',_binary ''),(64,'Andrea','Patata','andrea@gmail.com','andrea','2025-09-11 21:23:36',_binary '\0');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-16 12:02:25
