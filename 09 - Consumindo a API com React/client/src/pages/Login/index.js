import React from 'react';
import './styles.css';

import logo from '../../assets/dragon.svg'
import padlock from '../../assets/padlock.png'

export default function login() {
    return (
       <div className='login-container'>
           <section className="form">
                <img src={logo} alt="ntt" width="320" height="205" />
                <form>
                    <h1>Access your Account</h1>
                    <input placeholder='Username' />
                    <input type='password' placeholder='Password'/>

                    <button className='button' type='submit'>Login</button>
                </form>
           </section>
           <img src={padlock} alt="Login" width="320" height="205" />
       </div>
    )
}