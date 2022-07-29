package com.example.batmanproject.Models.DetailMovie

data class DetailMovie(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
) : MutableList<DetailMovie> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: DetailMovie): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<DetailMovie>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): DetailMovie {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: DetailMovie): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): MutableIterator<DetailMovie> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: DetailMovie): Int {
        TODO("Not yet implemented")
    }

    override fun add(element: DetailMovie): Boolean {
        TODO("Not yet implemented")
    }

    override fun add(index: Int, element: DetailMovie) {
        TODO("Not yet implemented")
    }

    override fun addAll(index: Int, elements: Collection<DetailMovie>): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAll(elements: Collection<DetailMovie>): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun listIterator(): MutableListIterator<DetailMovie> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): MutableListIterator<DetailMovie> {
        TODO("Not yet implemented")
    }

    override fun remove(element: DetailMovie): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<DetailMovie>): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): DetailMovie {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<DetailMovie>): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: DetailMovie): DetailMovie {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<DetailMovie> {
        TODO("Not yet implemented")
    }
}