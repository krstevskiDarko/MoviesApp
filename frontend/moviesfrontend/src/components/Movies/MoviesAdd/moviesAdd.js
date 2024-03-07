import React from "react";
import { useNavigate } from 'react-router-dom';

const ProductAdd = (props) => {

    const navigate = useNavigate()

    const [formData, updateFormData] = React.useState({
        title: "",
        description: "",
        year: 2000,
        genre: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) =>{
        e.preventDefault();

        const title = formData.title;
        const description = formData.description;
        const genre = formData.genre;
        const year = formData.year;

        props.onAddProduct(title,description,genre,year)

        navigate('/movies')
    }


    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group my-3">
                        <label htmlFor="title" className={"h5"}>Movie Title</label>
                        <input type="text"
                               className="form-control"
                               id="title"
                               name="title"
                               required
                               placeholder="Movie Title"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group my-3">
                        <label htmlFor="description" className={"h5"}>Movie Description</label>
                        <input type="text"
                               className="form-control"
                               id="description"
                               name="description"
                               placeholder="Movie Description"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group my-3">
                        <label htmlFor="genre" className={"h5"}>Movie Genre</label>
                        <input type="text"
                               className="form-control"
                               id="genre"
                               name="genre"
                               placeholder="Movie Genre"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group my-3">
                        <label htmlFor="year" className={"h5"}>Movie Year</label>
                        <input type="number"
                               className="form-control"
                               id="year"
                               name="year"
                               placeholder="Year"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-success">Submit</button>
                </form>
            </div>
        </div>

    )
}

export default ProductAdd;