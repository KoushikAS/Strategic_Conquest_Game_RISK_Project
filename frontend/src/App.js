import 'bootstrap/dist/css/bootstrap.min.css';
import './styles/App.css';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import GameView from './game/GameView';
import AttackView from './game/AttackView';
import LoginView from './login/LoginView'

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<GameView />} />
        <Route exact path="/attack" element={<AttackView />} />
        <Route path="/login" element={<LoginView />} />
      </Routes>
    </Router>
  );
}

export default App;
