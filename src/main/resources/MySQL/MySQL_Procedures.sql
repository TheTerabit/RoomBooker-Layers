DELIMITER //

DROP PROCEDURE IF EXISTS get_all_reservations //

CREATE PROCEDURE
  get_all_reservations( )
BEGIN
   SELECT * FROM reservation r join topic t on r.topic_id=t.topic_id;
END
//

DELIMITER ;


DELIMITER //

DROP PROCEDURE IF EXISTS filldb //

CREATE PROCEDURE
  filldb( )
BEGIN
  delete from user;
  DELETE from company;
  delete from room;
  delete from room_location;
  DELETE FROM user_password;
  DELETE from user_contact;
  DELETE from user_address;
  DELETE from job_title;
  DELETE FROM reservation;
  DELETE from topic;
END
//

DELIMITER ;

