create schema if not exists DISHES;

-- table DISH
create table if not exists DISHES.DISH(dishId int auto_increment primary key, dishName varchar(100) not null, dishDescr varchar(200));

-- table PRODUCT
create table if not exists DISHES.PRODUCT(productId int auto_increment primary key, productName varchar(100) not null, productDescr varchar(200));

-- table DISH PRODUCT
create table if not exists DISHES.DISH_PRODUCT(dish_productId int auto_increment primary key, dishId int not null, productId int not null, quantity int, unit varchar(20), descr varchar(50),
foreign key (dishId) references DISHES.DISH(dishId), foreign key (productId) references DISHES.PRODUCT(productId));

alter table DISHES.DISH_PRODUCT alter column DISH_PRODUCTID int not null auto_increment;

insert into DISHES.DISH values (default, 'Омлет', 'Завтрак: блюдо из яиц');
insert into DISHES.DISH values (default, 'Творожная запеканка', 'Завтрак: блюдо из творога');
insert into DISHES.DISH values (default, 'Котлеты мясные', 'Обед');

insert into DISHES.PRODUCT values (default, 'Яйца', '');
insert into DISHES.PRODUCT values (default, 'Молоко', '');
insert into DISHES.PRODUCT values (default, 'Мука', '');
insert into DISHES.PRODUCT values (default, 'Сода', '');
insert into DISHES.PRODUCT values (default, 'Соль', '');
insert into DISHES.PRODUCT values (default, 'Перец', '');

insert into DISHES.PRODUCT values (default, 'Творог', '');
insert into DISHES.PRODUCT values (default, 'Сахар', '');
insert into DISHES.PRODUCT values (default, 'Крупа манная', '');

insert into DISHES.PRODUCT values (default, 'Говядина', '');
insert into DISHES.PRODUCT values (default, 'Свинина', '');
insert into DISHES.PRODUCT values (default, 'Картофель', '');
insert into DISHES.PRODUCT values (default, 'Морковь', '');
insert into DISHES.PRODUCT values (default, 'Лук', '');

commit;

insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Омлет'), (select p.productId from DISHES.product as p where p.productName='Яйца'), 4, 'шт.', '');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Омлет'), (select p.productId from DISHES.product as p where p.productName='Молоко'), 1, 'стакан', '');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Омлет'), (select p.productId from DISHES.product as p where p.productName='Мука'), 2, 'ст.л.', '');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Омлет'), (select p.productId from DISHES.product as p where p.productName='Сода'), 3, 'шт.', '');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Омлет'), (select p.productId from DISHES.product as p where p.productName='Соль'), null, '', 'добавить по вкусу');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Омлет'), (select p.productId from DISHES.product as p where p.productName='Перец'), null, '', 'добавить по вкусу');

insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Творожная запеканка'), (select p.productId from DISHES.product as p where p.productName='Творог'), 200, 'грамм', '');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Творожная запеканка'), (select p.productId from DISHES.product as p where p.productName='Яйца'), 2, 'шт.', '');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Творожная запеканка'), (select p.productId from DISHES.product as p where p.productName='Сахар'), 2, 'ст. л.', '');
insert into DISHES.DISH_PRODUCT values (default, (select d.dishId from DISHES.DISH as d where d.dishName='Творожная запеканка'), (select p.productId from DISHES.product as p where p.productName='Крупа манная'), 2, 'ст. л.', '');

commit;