package almanac

import java.math.BigInteger

class AlmanacParser(
    private val seedsParser: SeedsParser,
) {

    private var seeds: Iterable<BigInteger>? = null

    private var parsing: MutableList<AlmanacMap.Range>? = null

    private val seedToSoil = mutableListOf<AlmanacMap.Range>()
    private val soilToFertilizer = mutableListOf<AlmanacMap.Range>()
    private val fertilizerToWater = mutableListOf<AlmanacMap.Range>()
    private val waterToLight = mutableListOf<AlmanacMap.Range>()
    private val lightToTemperature = mutableListOf<AlmanacMap.Range>()
    private val temperatureToHumidity = mutableListOf<AlmanacMap.Range>()
    private val humidityToLocation = mutableListOf<AlmanacMap.Range>()

    fun buildParsed(): Almanac {
        requireNotNull(seeds)
        require(seedToSoil.isNotEmpty())
        require(soilToFertilizer.isNotEmpty())
        require(fertilizerToWater.isNotEmpty())
        require(waterToLight.isNotEmpty())
        require(lightToTemperature.isNotEmpty())
        require(temperatureToHumidity.isNotEmpty())
        require(humidityToLocation.isNotEmpty())

        return Almanac(seeds = requireNotNull(seeds), maps = listOf(
            AlmanacMap(seedToSoil),
            AlmanacMap(soilToFertilizer),
            AlmanacMap(fertilizerToWater),
            AlmanacMap(waterToLight),
            AlmanacMap(lightToTemperature),
            AlmanacMap(temperatureToHumidity),
            AlmanacMap(humidityToLocation),
        ))
    }

    fun parseLine(line: String) {
        when {
            line.isBlank() -> parsing = null
            seeds == null -> seeds = seedsParser.parseSeeds(line)
            line.startsWith("seed-to-soil map:") -> parsing = seedToSoil
            line.startsWith("soil-to-fertilizer map:") -> parsing = soilToFertilizer
            line.startsWith("fertilizer-to-water map:") -> parsing = fertilizerToWater
            line.startsWith("water-to-light map:") -> parsing = waterToLight
            line.startsWith("light-to-temperature map:") -> parsing = lightToTemperature
            line.startsWith("temperature-to-humidity map:") -> parsing = temperatureToHumidity
            line.startsWith("humidity-to-location map:") -> parsing = humidityToLocation
            parsing != null -> parsing!!.add(AlmanacMap.Range.parse(line))
        }
    }
}