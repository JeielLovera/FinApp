use dbfinanzas;
select*from facturas;
select*from factorings;
select*from usuarios;
select*from tipointereses;
select*from gasto_factorings;
select*from factorings;
select*from gastos;
select*from carterafacturas;

insert into tipousuarios values (1,"empresa");
insert into tipousuarios values (2,"persona natural");
insert into tipointereses values (1,"efectiva");
insert into tipointereses values (2,"nominal");
insert into usuarios values(1,"usertest","usuario1","123456","usergmail","72767516","72767516123",2);
insert into facturas values(1,"2019-11-21","2019-12-22",12500,"factura venta de tv's","soles",1);
insert into facturas values(2,"2019-11-21","2019-12-30",15000,"factura venta de laptops","soles",1);
insert into facturas values(3,"2019-11-21","2020-01-15",14250,"factura venta de impresoras","soles",1);
insert into gastos values(1,"Portes");
insert into gastos values(2,"Fotocopias");
insert into gastos values(3,"Comisión de estudio");
insert into gastos values(4,"Comisión de intermediación");
insert into gastos values(5,"Gastos de administración");
insert into gastos values(6,"Gastos notariales");
insert into gastos values(7,"Seguro");
insert into gastos values(8,"Otros gastos");

