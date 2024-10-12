
#library(RMySQL)
#cons <- dbListConnections(MySQL())
#for(con in cons)dbDisconnect(con)

library(DBI)

con <- DBI::dbConnect(RMySQL::MySQL(), dbname = 'nurse_housing', host = "localhost", port = 3306, user = "root", password = "root1234")

table_age <- dbGetQuery(con, "SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age FROM nurse_housing.user where gender='female';")

newyear <- dbGetQuery(con, "SELECT YEAR(checkout_time) as year1 FROM nurse_housing.user where gender='female';")

dbDisconnect(con)
#print(table_age)

for ( i in table_age) {
  table_age1 <- i
}


for ( i in newyear) {
  year1 <- i
}

relation <- lm(year1~table_age1)
pre_time <- data.frame(table_age1 = 85)
result <-  predict(relation,pre_time)
print(result)


# Plot the chart
plot(year1,table_age1,
     col = "blue",
     xlab = "year",
     ylab = "age",
     main = "Life vs Year (2000-2015 Female)",
     abline(lm(table_age1~year1)),
     cex = 1.3,pch = 16
)

png(file = "age_year.png")

# Save the file.
dev.off()












