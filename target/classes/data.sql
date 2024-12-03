INSERT INTO DISH (id, name, description, price, image) 
SELECT 1212, 'Cheddar Burguer', 'Pão, 1 carne, 3 fatias de queijo cheddar, tomate, alface e cebola', 27.0, '/assets/images/cheddarburguer.png'
WHERE NOT EXISTS (SELECT 1 FROM DISH WHERE id = 1212);

INSERT INTO DISH (id, name, description, price, image)
SELECT 1231, 'Suco 600ml', 'Laranja - Limão - Maracujá', 8.0, '/assets/images/suco.jpg'
WHERE NOT EXISTS (SELECT 1 FROM DISH WHERE id = 1231);

INSERT INTO DISH (id, name, description, price, image)
SELECT 1452, 'Chicken Burguer', 'Pão, 1 filé de frango, 1 fatia de queijo, tomate, alface e cebola', 27.0, '/assets/images/ChickenBurguer.webp'
WHERE NOT EXISTS (SELECT 1 FROM DISH WHERE id = 1452);