import React from 'react';
import {Route, Switch, withRouter} from 'react-router-dom';
import Login from './Login'
import TransactionsTable from "./TransactionsTable";

class Main extends React.Component{

    componentDidMount(){
       
    }

    render(){
        return (
            <Switch>
                <Route path='/' component={Login}/>
                <Route path='/table' component={TransactionsTable}/>
            </Switch>
        )

        
    };
}


export default withRouter(Main);