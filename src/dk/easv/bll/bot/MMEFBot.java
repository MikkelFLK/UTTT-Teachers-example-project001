/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.easv.bll.bot;

import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;

/**
 *
 * @author MikkelK
 */

public class MMEFBot implements IBot
{
    private static final String BOTNAME ="MMEF";

    @Override
    public IMove doMove(IGameState state)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBotName()
    {
       
   
        return BOTNAME;
    }
    }


