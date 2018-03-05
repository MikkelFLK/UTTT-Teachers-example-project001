/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.easv.bll.bot;

import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import java.util.List;
import java.util.Random;

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
           Random r = new Random();
        List<IMove> validMoves = state.getField().getAvailableMoves();
        return validMoves.get(r.nextInt(validMoves.size()));
    }

    @Override
    public String getBotName()
    {
       
   
        return BOTNAME;
    }
    }


