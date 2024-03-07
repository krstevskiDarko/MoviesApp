import React from "react";
import {useNavigate} from "react-router-dom";

const MovieRate = (props) => {

    const navigate = useNavigate()

    const [formData, updateFormData] = React.useState({
        rating: 0.0,
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) =>{
        e.preventDefault();

        const rating = formData.rating;

        props.onMovieRate(props.movie.id, rating)

        navigate('/movies')
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <div className="container">
                            <div>
                                {props.movie.title}
                            </div>
                        </div>
                        <label htmlFor="rating">Add your rating!</label>
                        <input type="number"
                               className="form-control"
                               id="rating"
                               name="rating"
                               min={"1"}
                               max={"10"}
                               step={"0.1"}
                               required
                               placeholder="Rating"
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default MovieRate;