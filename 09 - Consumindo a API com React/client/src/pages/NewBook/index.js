import React, { useState, useEffect } from "react";
import { useHistory, Link, useParams} from "react-router-dom";
import {FiArrowLeft} from 'react-icons/fi';

import './styles.css';

import logo from '../../assets/dragon.svg'

import api from "../../services/api";

export default function NewBook(){

    const [id, setId] = useState(null);
    const [author, setAuthor] = useState('');
    const [launchDate, setLaunchDate] = useState('');
    const [price, setPrice] = useState('');
    const [title, setTitle] = useState('');

    const {bookId} = useParams();

    const username =localStorage.getItem('username');
    const accessToken =localStorage.getItem('accessToken');

    const history = useHistory();

    async function loadBook() {
        try{
            const response = await api.get(`api/book/v1/${bookId}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            })
            let adjustedDate = response.data.launchDate.split("T", 10)[0];

            setId(response.data.id);
            setTitle(response.data.title);
            setAuthor(response.data.author);
            setPrice(response.data.price);
            setLaunchDate(adjustedDate)
        }catch(error) {
            alert('Error recovering book! Try again')
            history.push('/books');
        }
    }

    useEffect(() => {
        if (bookId === '0') return;
        else loadBook();
    })

    async function createNewBook(e){
        e.preventDefault();

        const data = 
        {
            title,
            author,
            launchDate,
            price
        }

        const header = 
        {
            Authorization: `Bearer ${accessToken}`
        }

        try
        {
           await api.post('api/book/v1', data, {
               headers:{
                   Authorization: `Bearer ${accessToken}`
               }
           });
           history.push('/books');
        }catch(err){
            alert('Error whle recording Book! Try again!')
        }

    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logo} alt="Ntt"/>
                    <h1>Add New Book</h1>
                    <p>Enter the book information and click 'Add' ! ### {bookId}</p>
                    <Link className="back-link" to="/books">
                        <FiArrowLeft size={16} color="#251fc5" />
                        Home
                    </Link>
                </section>
                <form onSubmit={createNewBook}>
                    <input 
                        placeholder="Title"
                        value={title}
                        onChange={e => setTitle(e.target.value)} 
                    />
                    <input 
                        placeholder="Author"
                        value={author}
                        onChange={e => setAuthor(e.target.value)}
                    />
                    <input 
                        type="date"
                        value={launchDate}
                        onChange={e => setLaunchDate(e.target.value)} 
                    />            
                    <input 
                        placeholder="Price"
                        value={price}
                        onChange={e => setPrice(e.target.value)}
                     />

                    <button className="button" type="submit">Add</button>
                </form>
            </div>
        </div>
    )
}