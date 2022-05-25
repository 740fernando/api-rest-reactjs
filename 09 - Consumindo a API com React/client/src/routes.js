import React from 'react';
import { BrowserRouter, Routes,Route, Link } from 'react-router-dom';

import Login from './pages/Login';
import Book from './pages/Book';

export default function App(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' exact element={<Login/>} />
                <Route path='/book' element={<Book/>} />
            </Routes>
        </BrowserRouter>
    );
}