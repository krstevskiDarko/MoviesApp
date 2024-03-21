import axios from '../custom-axios/axios';

const MoviesService = {
    fetchMovies: (title, genre, genres, year, yearFrom, yearTo, page) => {
        return axios.get(`/movies/filters`, { params: {
            title: title,
            genre: genre,
            genres: genres,
            year: year,
            yearFrom: yearFrom,
            yearTo: yearTo,
            page: page || 0,
            size: 4
        }})
    },
    fetchReviews: () => {
        return axios.get(`/reviews`)
    },
    movieDetails: (id) => {
        return axios.get(`/movies/${id}`);
    },
    getMovie: (id) => {
        return axios.get(`/movies/${id}`);
    }
    ,
    addMovie: (title, description, genre, year) => {
        return axios.post("/movies", {
            "title" : title,
            "description" : description,
            "genre" : genre,
            "year" : year
        })
    },
    createReview: (id, review) => {
        return axios.post(`/movies/${id}/review`,review)
    },
    getReview: (id) => {
        return axios.get(`/reviews/${id}`)
    },
    rateMovie: (id, rating) => {
        return axios.post(`/movies/${id}/rate`, rating, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

}

export default MoviesService;
