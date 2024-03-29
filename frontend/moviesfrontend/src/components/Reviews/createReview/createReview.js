import React from "react";
import {useNavigate} from "react-router-dom";

const CreateReview = (props) => {

    const navigate = useNavigate()

    const [formData, updateFormData] = React.useState({
        review: "",
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) =>{
        e.preventDefault();

        const review = formData.review;

        props.onCreateReview(props.movie.id, review)

        navigate('/reviews')
    }


    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <div className={"display-5 fw-bold"}>
                            {props.movie.title}
                        </div>
                        <hr></hr>
                        <label htmlFor="review" className={"h3"}>Add your review!</label>
                        <input type="text"
                               className="form-control"
                               id="review"
                               name="review"
                               required
                               placeholder="Review"
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary my-3">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default CreateReview;