INSERT INTO options(username, name)
VALUES('test1', 'Test1');
INSERT INTO options(username, name)
VALUES('test2', 'Test2');


INSERT INTO polls(id, title, subtitle, stopped)
VALUES('0ff8f479-c747-4a97-bb39-f61b401a8e0e', 'Poll test', 'Test', false);

INSERT INTO polls(id, title, subtitle, stopped)
VALUES('9a8537ac-d939-4ab8-844c-2254087757e5', 'Poll stop', 'Stop test', false);

INSERT INTO polls(id, title, subtitle, stopped)
VALUES('ce23f269-4dff-4171-aff8-5397af6e9828', 'Poll stopped', 'Stopped test', true);
