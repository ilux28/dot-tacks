
case class Car(number: String, year: Int)

val cars = Seq(
  Car("а121мм16", 2016),
  Car("а133мм16", 2011),
  Car("а123ох16", 2012),
  Car("в888хо178", 2022),
  Car("в777хх178", 2007),
  Car("в123хх77", 2002)
)

val res: Map[String, Int] = cars.groupBy(car => {
  val length = car.number.length

  val regionNumber = if (length == 8) {
    car.number.substring(6, 8)
  } else car.number.substring(6, 9)

  regionNumber
}).map(group => {
  val groupLength = group._2.size
  val sum = group._2.foldLeft(0)((acc, next) => acc + next.year)
  (group._1, sum / groupLength)

});
