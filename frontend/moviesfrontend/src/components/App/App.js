import React, { Component } from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import Movies from '../Movies/movies';
import MoviesService from "../../repository/moviesRepository";
import Reviews from "../Reviews/reviews";
import Header from "../Header/header";
import ProductAdd from "../Movies/MoviesAdd/moviesAdd";
import MovieDetails from "../Movies/MovieDetails/movieDetails";
import CreateReview from "../Reviews/createReview/createReview";
import MovieRate from "../Movies/MovieRate/movieRate";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: [],
            reviews: [],
            selectedMovie: {},
            selectedReviews: [],
            title: "",
            genre: "",
            genres: "",
            year: "",
            yearFrom: "",
            yearTo: ""
        }
    }

    render() {
        return (
            <Router history={this.props.history}>
                <Header/>
                <main>
                    <div className="container m-4">
                        <Routes>
                            <Route path={"/movies/add"} element={
                                <ProductAdd
                                    onAddProduct={this.addProduct}/>}/>
                            <Route path={"/movies/:id"} element={
                                <MovieDetails
                                    onMovieDetails={this.movieDetail}
                                    movie={this.state.selectedMovie}/>}/>
                            <Route path="/movies/:id/rate" element={
                                <MovieRate
                                    onMovieRate={this.rateMovie}
                                    movie={this.state.selectedMovie} />} />
                            <Route path="/movies" element={
                                <Movies
                                    selectMovie={this.getMovie}
                                    selectMovieReviews={this.getReview}
                                    onSearch={this.filterMovies}
                                    loadAllMovies={this.loadMovies}
                                    movies={this.state.movies} />} />
                            <Route path="/movies/:id/review" element={
                                <CreateReview
                                    onCreateReview={this.createReview}
                                    movie={this.state.selectedMovie} />} />
                            <Route path="/reviews" element={
                                <Reviews
                                    reviews={this.state.reviews} />} />
                            <Route
                                path="/"
                                element={ <Navigate to="/movies" /> }
                            />
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadMovies();
        this.loadReviews();
    }

    loadMovies = (title, genre, genres, year, yearFrom, yearTo) =>{
        MoviesService.fetchMovies(title, genre, genres, year, yearFrom, yearTo)
            .then((data) => {
                this.setState({
                    movies: data.data
                })
            });
    }

    filterMovies = (title, genre, genres, year, yearFrom, yearTo) => {
        this.setState({
            title: title,
            genre: genre,
            genres: genres,
            year: year,
            yearFrom: yearFrom,
            yearTo: yearTo
        }, () => {
            this.loadMovies(title, genre, genres, year, yearFrom, yearTo);
        });

    }
    loadReviews = () => {
        MoviesService.fetchReviews()
            .then((data)=> {
                this.setState({
                    reviews: data.data,
                })
            });
    }

    getMovie = (id) => {
        MoviesService.getMovie(id)
            .then((data) => {
                this.setState({
                    selectedMovie: data.data
                })
            })
    }

    // getReview = (id) => {
    //     MoviesService.getReview(id)
    //         .then((data) => {
    //             this.setState({
    //                 selectedReviews: data.data
    //             })
    //         })
    // }

    movieDetail = (id) => {
        MoviesService.movieDetails(id)
            .then(() => {
                this.loadMovies()
            })
    }

    addProduct = (title, description, genre, year) => {
        MoviesService.addMovie(title, description, genre, year)
            .then(() => {
                this.loadMovies()
            })
    }

    createReview = (id, review) => {
        MoviesService.createReview(id, review)
            .then(() => {
                this.loadMovies()
                this.loadReviews()
            })
    }

    rateMovie = (id, rating) => {
        MoviesService.rateMovie(id, rating)
            .then(() => {
                this.loadMovies()
            })
    }
}

export default App;
