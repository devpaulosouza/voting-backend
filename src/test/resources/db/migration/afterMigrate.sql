INSERT INTO options(username, name)
VALUES('test1', 'Test1');
INSERT INTO options(username, name)
VALUES('test2', 'Test2');
INSERT INTO options(username, name)
VALUES('delete', 'Option delete');


INSERT INTO polls(id, title, subtitle, stopped)
VALUES('0ff8f479-c747-4a97-bb39-f61b401a8e0e', 'Poll test get', 'Test', false);
INSERT INTO summarized_votes(poll_id, username, votes)
VALUES('0ff8f479-c747-4a97-bb39-f61b401a8e0e', 'test1', 1);
INSERT INTO summarized_votes(poll_id, username, votes)
VALUES('0ff8f479-c747-4a97-bb39-f61b401a8e0e', 'test2', 2);

INSERT INTO polls(id, title, subtitle, stopped)
VALUES('9a8537ac-d939-4ab8-844c-2254087757e5', 'Poll stop', 'Stop test', false);

INSERT INTO polls(id, title, subtitle, stopped)
VALUES('ce23f269-4dff-4171-aff8-5397af6e9828', 'Poll stopped', 'Stopped test', true);



INSERT INTO polls(id, title, subtitle, stopped)
VALUES('fab0b1fe-3fec-4d4b-8315-6a6ee917ef92', 'Vote test', 'Vote test', false);
INSERT INTO summarized_votes(poll_id, username, votes)
VALUES('fab0b1fe-3fec-4d4b-8315-6a6ee917ef92', 'test1', 0);
INSERT INTO summarized_votes(poll_id, username, votes)
VALUES('fab0b1fe-3fec-4d4b-8315-6a6ee917ef92', 'test2', 0);
