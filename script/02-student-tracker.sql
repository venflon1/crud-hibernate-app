# Creazione del database 'hb_student_tracker' se non esiste già
CREATE DATABASE  IF NOT EXISTS `hb_student_tracker`;

# Imposto database 'hb_student_tracker' come quello da usare per le successive operazioni
USE `hb_student_tracker`;


# Cancello la tabella 'student' se esiste già
DROP TABLE IF EXISTS `student`;

--
-- Table structure for table `student`
--

# Creo la tabella 'student'
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

