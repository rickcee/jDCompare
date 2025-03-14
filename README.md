# jDCompare



jdbc:h2:/Users/rickcee/jdcomparedb

org.h2.Driver
jdbc:h2:/Users/rickcee/sampledb1
jdbc:h2:/Users/rickcee/sampledb2

select ID, name, age, address, phone from students;
select cusip, sum(quantity) from trades group by cusip;
