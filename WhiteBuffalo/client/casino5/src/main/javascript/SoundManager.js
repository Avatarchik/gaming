goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.SoundManager");

amaya.game.SoundManager = function ( model, soundSystem ) {

    var instance = {};

    var gameServices = new amaya.GameServices ();
    var anticipation;
    var bonus_marker_01;
    var bonus_marker_02;
    var bonus_marker_03;
    var bonus_marker_04;
    var bonus_marker_05;
    var bigWin;
    var intro;
    var spinSound;
    var reelSpin;
    var reelStopSound;
    var winTickUp;
    var spinButton;
    var freeSpinSound;
    var Transition;
    var freeOutro;
    var freeIntro;
    var freeRetrigg;
    var reelStrikeSound;
  //  var PreBigWinBlueSound;
  //  var PreBigWinRedSound;
    var sym1, fwSym2, Sym3, Sym4, Sym5, Sym6, Ace, King, Queen, Jack, Ten, Nine, scatter, wildSoundMix;

    instance.PlayAnticipation = function () {
        if(anticipation){
            anticipation.stop();
            createjs.Ticker.removeEventListener("tick",anticipationComplete);
        }
        anticipation = soundSystem.play(amaya.game.SoundConstants.ANTICIPATION.id,0,amaya.game.SoundConstants.ANTICIPATION.start);
        createjs.Ticker.addEventListener("tick",anticipationComplete);
    };

    var anticipationComplete = function(e){
        var currPos = anticipation.getPosition();
        if (currPos >= amaya.game.SoundConstants.ANTICIPATION.end){
            if(amaya.game.SoundConstants.ANTICIPATION.loop){
                anticipation.setPosition(amaya.game.SoundConstants.ANTICIPATION.start);
            }else{
                anticipation.stop();
                createjs.Ticker.removeEventListener("tick",anticipationComplete);
            }
        }
    };

    instance.StopAnticipation = function () {
         if(anticipation){
            anticipation.stop();
            createjs.Ticker.removeEventListener("tick",anticipationComplete);
        }
    };

    instance.PlayBonus_marker_02 = function () {
        if(bonus_marker_02){
              bonus_marker_02.stop();
              createjs.Ticker.removeEventListener("tick",bonus_marker_02_Complete);
        }
        bonus_marker_02 = soundSystem.play(amaya.game.SoundConstants.BONUS_MARKER_2.id,0,amaya.game.SoundConstants.BONUS_MARKER_2.start);
        createjs.Ticker.addEventListener("tick",bonus_marker_02_Complete);
    };

    var bonus_marker_02_Complete = function(e){
        var currPos = bonus_marker_02.getPosition();
        if (currPos >= amaya.game.SoundConstants.BONUS_MARKER_2.end){
            if(amaya.game.SoundConstants.BONUS_MARKER_2.loop){
                bonus_marker_02.setPosition(amaya.game.SoundConstants.BONUS_MARKER_2.start);
            }else{
                bonus_marker_02.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_02_Complete);
            }
        }
    };

    instance.PlayBonus_marker_03 = function () {
        if(bonus_marker_03){
            bonus_marker_03.stop();
            createjs.Ticker.removeEventListener("tick",bonus_marker_03_Complete);
        }
        bonus_marker_03 = soundSystem.play(amaya.game.SoundConstants.BONUS_MARKER_3.id,0,amaya.game.SoundConstants.BONUS_MARKER_3.start);
        createjs.Ticker.addEventListener("tick",bonus_marker_03_Complete);
    };

    var bonus_marker_03_Complete = function(e){
        var currPos = bonus_marker_03.getPosition();
        if (currPos >= amaya.game.SoundConstants.BONUS_MARKER_3.end){
            if(amaya.game.SoundConstants.BONUS_MARKER_3.loop){
                bonus_marker_03.setPosition(amaya.game.SoundConstants.BONUS_MARKER_3.start);
            }else{
                bonus_marker_03.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_03_Complete);
            }
        }
    };

    instance.PlayBonus_marker_04 = function () {
           if(bonus_marker_04){
                bonus_marker_04.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_04_Complete);
           }
            bonus_marker_04 = soundSystem.play(amaya.game.SoundConstants.BONUS_MARKER_4.id,0,amaya.game.SoundConstants.BONUS_MARKER_4.start);
            createjs.Ticker.addEventListener("tick",bonus_marker_04_Complete);
    };

    var bonus_marker_04_Complete = function(e){
        var currPos = bonus_marker_04.getPosition();
        if (currPos >= amaya.game.SoundConstants.BONUS_MARKER_4.end){
            if(amaya.game.SoundConstants.BONUS_MARKER_4.loop){
                bonus_marker_04.setPosition(amaya.game.SoundConstants.BONUS_MARKER_4.start);
            }else{
                bonus_marker_04.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_04_Complete);
            }
        }
    };

    instance.PlayBigWin = function () {
        if(bigWin){
            bigWin.stop();
            createjs.Ticker.removeEventListener("tick",BigWinComplete);
        }

        bigWin = soundSystem.play(amaya.game.SoundConstants.BIGWIN.id,0,amaya.game.SoundConstants.BIGWIN.start);
        createjs.Ticker.addEventListener("tick",BigWinComplete);
    };

    var BigWinComplete = function(e){
        var currPos = bigWin.getPosition();
        if (currPos >= amaya.game.SoundConstants.BIGWIN.end){
            if(amaya.game.SoundConstants.BIGWIN.loop){
                bigWin.setPosition(amaya.game.SoundConstants.BIGWIN.start);
            }else{
                bigWin.stop();
                createjs.Ticker.removeEventListener("tick",BigWinComplete);
            }
        }
    };

    instance.StopBigWin = function () {
        if (bigWin) {
            bigWin.stop();
            createjs.Ticker.removeEventListener("tick",BigWinComplete);
        }
    };


    instance.PlayIntro = function () {
        if(intro) {
            intro.stop();
            createjs.Ticker.removeEventListener("tick",IntroComplete);
        }

        intro = soundSystem.play(amaya.game.SoundConstants.INTRO.id,0,amaya.game.SoundConstants.INTRO.start);
        createjs.Ticker.addEventListener("tick",IntroComplete);
    };

    var IntroComplete = function(e){
        var currPos = intro.getPosition();
        if (currPos >= amaya.game.SoundConstants.INTRO.end){
            if(amaya.game.SoundConstants.INTRO.loop){
                intro.setPosition(amaya.game.SoundConstants.INTRO.start);
            }else{
                intro.stop();
                createjs.Ticker.removeEventListener("tick",IntroComplete);
            }
        }
    };

     instance.StopIntroSound = function () {
            if (intro) {
                intro.stop();
                createjs.Ticker.removeEventListener("tick",IntroComplete);
            }
     };

    var spinSoundConstant;
    var x =1;
    instance.PlaySpinSound = function () {
            if(spinSound){
                 spinSound.stop();
                 createjs.Ticker.removeEventListener("tick",spinSoundComplete);
                }
           //  x = Math.floor((Math.random() * 4) +1);
            reelSpin = 'REEL_SPIN' +x;
            if(x==4){
                 x=1;
             }
             else{
                 x++;
             }
            spinSound = soundSystem.play(amaya.game.SoundConstants[reelSpin].id,0,amaya.game.SoundConstants[reelSpin].start);
            spinSoundConstant = amaya.game.SoundConstants[reelSpin];
            createjs.Ticker.addEventListener("tick",spinSoundComplete);
    };

    var spinSoundComplete = function(e){
        var currPos = spinSound.getPosition();
        if (currPos >= spinSoundConstant.end){
            if(spinSoundConstant.loop){
                spinSound.setPosition(spinSoundConstant.start);
            }else{
                spinSound.stop();
                createjs.Ticker.removeEventListener("tick",spinSoundComplete);
            }
        }
    };

    instance.StopSpinSound = function () {
            if (spinSound) {
                spinSound.stop();
                createjs.Ticker.removeEventListener("tick",spinSoundComplete);
            }
    };

    instance.PlayReelStopSound = function () {
        if (reelStopSound) {
            reelStopSound.stop();
            createjs.Ticker.removeEventListener("tick",reelStopSoundComplete);
        }
        reelStopSound = soundSystem.play(amaya.game.SoundConstants.REEL_STOP.id,0,amaya.game.SoundConstants.REEL_STOP.start);
        createjs.Ticker.addEventListener("tick",reelStopSoundComplete);
    };

    var reelStopSoundComplete = function(e){
        var currPos = reelStopSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.REEL_STOP.end){
            if(amaya.game.SoundConstants.REEL_STOP.loop){
                reelStopSound.setPosition(amaya.game.SoundConstants.REEL_STOP.start);
            }else{
                reelStopSound.stop();
                createjs.Ticker.removeEventListener("tick",reelStopSoundComplete);
            }
        }
    };

    instance.PlayReelSrikeSound = function () {
        if (reelStrikeSound) {
            reelStrikeSound.stop();
            createjs.Ticker.removeEventListener("tick",reelStrikeSoundComplete);
        }
        reelStrikeSound = soundSystem.play(amaya.game.SoundConstants.REEL_STRIKE.id,0,amaya.game.SoundConstants.REEL_STRIKE.start);
        createjs.Ticker.addEventListener("tick",reelStrikeSoundComplete);
    };

    var reelStrikeSoundComplete = function(e){
        var currPos = reelStrikeSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.REEL_STRIKE.end){
            if(amaya.game.SoundConstants.REEL_STRIKE.loop){
                reelStrikeSound.setPosition(amaya.game.SoundConstants.REEL_STRIKE.start);
            }else{
                reelStrikeSound.stop();
                createjs.Ticker.removeEventListener("tick",reelStrikeSoundComplete);
            }
        }
    };



    instance.PlayWinTickUp = function () {
        if(winTickUp){
            winTickUp.stop();
            createjs.Ticker.removeEventListener("tick",WinTickUpComplete);
        }

        winTickUp = soundSystem.play(amaya.game.SoundConstants.BASE_WIN.id,0,amaya.game.SoundConstants.BASE_WIN.start);
        createjs.Ticker.addEventListener("tick",WinTickUpComplete);
    };

    var WinTickUpComplete = function(e){
        var currPos = winTickUp.getPosition();
        if (currPos >= amaya.game.SoundConstants.BASE_WIN.end){
            if(amaya.game.SoundConstants.BASE_WIN.loop){
                winTickUp.setPosition(amaya.game.SoundConstants.BASE_WIN.start);
            }else{
                winTickUp.stop();
                createjs.Ticker.removeEventListener("tick",WinTickUpComplete);
            }
        }
    };

    instance.StopWinTickUp = function () {
        if (winTickUp) {
            winTickUp.stop();
            createjs.Ticker.removeEventListener("tick",WinTickUpComplete);
        }
    };

    instance.PlaySpinButton = function () {
        if (spinButton) {
            spinButton.stop();
            createjs.Ticker.removeEventListener("tick",SpinButtonComplete);
        }
        spinButton = soundSystem.play(amaya.game.SoundConstants.SPIN.id,0,amaya.game.SoundConstants.SPIN.start);
        createjs.Ticker.addEventListener("tick",SpinButtonComplete);
    };

    var SpinButtonComplete = function(e){
        var currPos = spinButton.getPosition();
        if (currPos >= amaya.game.SoundConstants.SPIN.end){
            if(amaya.game.SoundConstants.SPIN.loop){
                spinButton.setPosition(amaya.game.SoundConstants.SPIN.start);
            }else{
                spinButton.stop();
                createjs.Ticker.removeEventListener("tick",SpinButtonComplete);
            }
        }
    };

    instance.PlayFreeSpinSound = function () {
          if (freeSpinSound) {
                freeSpinSound.stop();
                createjs.Ticker.removeEventListener("tick",freeSpinSoundComplete);
            }
        freeSpinSound = soundSystem.play(amaya.game.SoundConstants.FREE_SPIN.id,0,amaya.game.SoundConstants.FREE_SPIN.start);
        createjs.Ticker.addEventListener("tick",freeSpinSoundComplete);
    };

    var freeSpinSoundComplete = function(e){
        var currPos = freeSpinSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.FREE_SPIN.end){
            if(amaya.game.SoundConstants.FREE_SPIN.loop){
                freeSpinSound.setPosition(amaya.game.SoundConstants.FREE_SPIN.start);
            }else{
                freeSpinSound.stop();
                createjs.Ticker.removeEventListener("tick",freeSpinSoundComplete);
            }
        }
    };

    instance.pauseFreeSpinSound = function () {
            if (freeSpinSound) {
                freeSpinSound.pause();
            }
    };

    instance.resumeFreeSpinSound = function () {
              freeSpinSound.resume();
    };

    instance.StopFreeSpinSound = function () {
            if (freeSpinSound) {
                freeSpinSound.stop();
                createjs.Ticker.removeEventListener("tick",freeSpinSoundComplete);
            }
    };

     instance.PlayTransitionSound = function () {
            if(Transition){
                 Transition.stop();
                 createjs.Ticker.removeEventListener("tick",TransitionComplete);
            }
            Transition = soundSystem.play(amaya.game.SoundConstants.TRANSITION.id,0,amaya.game.SoundConstants.TRANSITION.start);
            createjs.Ticker.addEventListener("tick",TransitionComplete);
     };

    var TransitionComplete = function(e){
        var currPos = Transition.getPosition();
        if (currPos >= amaya.game.SoundConstants.TRANSITION.end){
            if(amaya.game.SoundConstants.TRANSITION.loop){
                Transition.setPosition(amaya.game.SoundConstants.TRANSITION.start);
            }else{
                Transition.stop();
                createjs.Ticker.removeEventListener("tick",TransitionComplete);
            }
        }
    };

    instance.StopTransitionSound = function () {
        if (Transition) {
            Transition.stop();
            createjs.Ticker.removeEventListener("tick",TransitionComplete);
        }
    };



    instance.PlayFreeOutroSound = function () {
        if (freeOutro) {
            freeOutro.stop();
            createjs.Ticker.removeEventListener("tick",FreeOutroComplete);
        }
            freeOutro = soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_OUTRO.id,0,amaya.game.SoundConstants.FREE_SPIN_OUTRO.start);
            createjs.Ticker.addEventListener("tick",FreeOutroComplete);
    };

    var FreeOutroComplete = function(e){
        var currPos = freeOutro.getPosition();
        if (currPos >= amaya.game.SoundConstants.FREE_SPIN_OUTRO.end){
            if(amaya.game.SoundConstants.FREE_SPIN_OUTRO.loop){
                freeOutro.setPosition(amaya.game.SoundConstants.FREE_SPIN_OUTRO.start);
            }else{
                freeOutro.stop();
                createjs.Ticker.removeEventListener("tick",FreeOutroComplete);
            }
        }
    };

     instance.PlayFreeRetriggSound = function () {
        if (freeRetrigg) {
            freeRetrigg.stop();
            createjs.Ticker.removeEventListener("tick",FreeRetriggComplete);
        }
            freeRetrigg = soundSystem.play(amaya.game.SoundConstants.RETRIGGER.id,0,amaya.game.SoundConstants.RETRIGGER.start);
            createjs.Ticker.addEventListener("tick",FreeRetriggComplete);
    };

    var FreeRetriggComplete = function(e){
        var currPos = freeRetrigg.getPosition();
        if (currPos >= amaya.game.SoundConstants.RETRIGGER.end){
            if(amaya.game.SoundConstants.RETRIGGER.loop){
                freeRetrigg.setPosition(amaya.game.SoundConstants.RETRIGGER.start);
            }else{
                freeRetrigg.stop();
                createjs.Ticker.removeEventListener("tick",FreeRetriggComplete);
            }
        }
    };

     instance.StopFreeRetriggSound = function () {
        if (freeRetrigg) {
            freeRetrigg.stop();
            createjs.Ticker.removeEventListener("tick",FreeRetriggComplete);
        }
     };

    var wildSound = function () {
        if(sym1){
           sym1.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete1);
        }
        sym1 = soundSystem.play(amaya.game.SoundConstants.WILD.id,0,amaya.game.SoundConstants.WILD.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete1);
    };
    var WinAnimComplete1 = function(e){
        var currPos = sym1.getPosition();
        if (currPos >= amaya.game.SoundConstants.WILD.end){
            if(amaya.game.SoundConstants.WILD.loop){
                sym1.setPosition(amaya.game.SoundConstants.WILD.start);
            }else{
                sym1.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete1);
            }
        }
    };

    var Sym2Sound = function () {
        if(fwSym2){
          fwSym2.stop();
          createjs.Ticker.removeEventListener("tick",WinAnimComplete2);
        }

        fwSym2 = soundSystem.play(amaya.game.SoundConstants.SYM2.id,0,amaya.game.SoundConstants.SYM2.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete2);
    };

    var WinAnimComplete2 = function(e){
        var currPos = fwSym2.getPosition();
        if (currPos >= amaya.game.SoundConstants.SYM2.end){
            if(amaya.game.SoundConstants.SYM2.loop){
                fwSym2.setPosition(amaya.game.SoundConstants.SYM2.start);
            }else{
                fwSym2.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete2);
            }
        }
    };

    var Sym3Sound = function () {
        if(Sym3){
           Sym3.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete3);
        }
        Sym3 = soundSystem.play(amaya.game.SoundConstants.SYM3.id,0,amaya.game.SoundConstants.SYM3.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete3);
    };

    var WinAnimComplete3 = function(e){
        var currPos = Sym3.getPosition();
        if (currPos >= amaya.game.SoundConstants.SYM3.end){
            if(amaya.game.SoundConstants.SYM3.loop){
                Sym3.setPosition(amaya.game.SoundConstants.SYM3.start);
            }else{
                Sym3.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete3);
            }
        }
    };

    var Sym4Sound = function () {
        if(Sym4){
            Sym4.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete4);
        }
        Sym4 = soundSystem.play(amaya.game.SoundConstants.SYM4.id,0,amaya.game.SoundConstants.SYM4.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete4);
    };

    var WinAnimComplete4 = function(e){
        var currPos = Sym4.getPosition();
        if (currPos >= amaya.game.SoundConstants.SYM4.end){
            if(amaya.game.SoundConstants.SYM4.loop){
                Sym4.setPosition(amaya.game.SoundConstants.SYM4.start);
            }else{
                Sym4.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete4);
            }
        }
    };

    var Sym5Sound = function () {
        if(Sym5){
           Sym5.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete5);
        }

        Sym5 = soundSystem.play(amaya.game.SoundConstants.SYM5.id,0,amaya.game.SoundConstants.SYM5.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete5);
        //alert("sound position: "+Sym5.getPosition());
    };

    var WinAnimComplete5 = function(e){
        var currPos = Sym5.getPosition();
        if (currPos >= amaya.game.SoundConstants.SYM5.end){
            if(amaya.game.SoundConstants.SYM5.loop){
                Sym5.setPosition(amaya.game.SoundConstants.SYM5.start);
            }else{
                Sym5.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete5);
            }
        }
    };

    var Sym6Sound = function () {
        if(Sym6){
           Sym6.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete6);
        }

        Sym6 = soundSystem.play(amaya.game.SoundConstants.SYM6.id,0,amaya.game.SoundConstants.SYM6.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete6);
        //alert("sound position: "+Sym6.getPosition());
    };

    var WinAnimComplete6 = function(e){
        var currPos = Sym6.getPosition();
        if (currPos >= amaya.game.SoundConstants.SYM6.end){
            if(amaya.game.SoundConstants.SYM6.loop){
                Sym6.setPosition(amaya.game.SoundConstants.SYM6.start);
            }else{
                Sym6.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete6);
            }
        }
    };

    var aceSound = function () {
        if(Ace){
           Ace.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
        }

        Ace = soundSystem.play(amaya.game.SoundConstants.ACE.id,0,amaya.game.SoundConstants.ACE.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete7);
    };

    var WinAnimComplete7 = function(e){
        var currPos = Ace.getPosition();
        if (currPos >= amaya.game.SoundConstants.ACE.end){
            if(amaya.game.SoundConstants.ACE.loop){
                Ace.setPosition(amaya.game.SoundConstants.ACE.start);
            }else{
                Ace.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
            }
        }
    };

    var kingSound = function () {
        if(King){
           King.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
        }
        King = soundSystem.play(amaya.game.SoundConstants.KING.id,0,amaya.game.SoundConstants.KING.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete8);
    };

    var WinAnimComplete8 = function(e){
        var currPos = King.getPosition();
        if (currPos >= amaya.game.SoundConstants.KING.end){
            if(amaya.game.SoundConstants.KING.loop){
                King.setPosition(amaya.game.SoundConstants.KING.start);
            }else{
                King.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete8);
            }
        }
    };

    var queenSound = function () {
        if(Queen){
           Queen.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete9);
        }
        Queen = soundSystem.play(amaya.game.SoundConstants.QUEEN.id,0,amaya.game.SoundConstants.QUEEN.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete9);
    };

    var WinAnimComplete9 = function(e){
        var currPos = Queen.getPosition();
        if (currPos >= amaya.game.SoundConstants.QUEEN.end){
            if(amaya.game.SoundConstants.QUEEN.loop){
                Queen.setPosition(amaya.game.SoundConstants.QUEEN.start);
            }else{
                Queen.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete9);
            }
        }
    };

    var jackSound = function () {
        if(Jack){
            Jack.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete10);
        }
        Jack = soundSystem.play(amaya.game.SoundConstants.JACK.id,0,amaya.game.SoundConstants.JACK.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete10);
    };

    var WinAnimComplete10 = function(e){
        var currPos = Jack.getPosition();
        if (currPos >= amaya.game.SoundConstants.JACK.end){
            if(amaya.game.SoundConstants.JACK.loop){
                Jack.setPosition(amaya.game.SoundConstants.JACK.start);
            }else{
                Jack.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete10);
            }
        }
    };

    var bonusSound = function () {
        if(scatter){
           scatter.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete11);
        }
        scatter = soundSystem.play(amaya.game.SoundConstants.SCATTER.id,0,amaya.game.SoundConstants.SCATTER.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete11);
    };

    var WinAnimComplete11 = function(e){
        var currPos = scatter.getPosition();
        if (currPos >= amaya.game.SoundConstants.SCATTER.end){
            if(amaya.game.SoundConstants.SCATTER.loop){
                scatter.setPosition(amaya.game.SoundConstants.SCATTER.start);
            }else{
                scatter.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete11);
            }
        }
    };

    var symSound = [wildSound,Sym2Sound,Sym3Sound,Sym4Sound,Sym5Sound,Sym6Sound,aceSound,kingSound,queenSound,jackSound,bonusSound];

    instance.PlayWinAnim = function (sumId) {
        //alert("Call PlayWinAnim");
        symSound[sumId-1]();
    };

 instance.RemoveSymSounds = function () {

        if(bonus_marker_02){
          bonus_marker_02.stop();
          createjs.Ticker.removeEventListener("tick",bonus_marker_02_Complete);
        }

        if(bonus_marker_03){
            bonus_marker_03.stop();
            createjs.Ticker.removeEventListener("tick",bonus_marker_03_Complete);
        }

         if(bonus_marker_04){
            bonus_marker_04.stop();
            createjs.Ticker.removeEventListener("tick",bonus_marker_04_Complete);
         }
        if(sym1){
            sym1.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete1);
         }

        if(fwSym2){
             fwSym2.stop();
             createjs.Ticker.removeEventListener("tick",WinAnimComplete2);
        }

        if(Sym3){
           Sym3.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete3);
        }

         if(Sym4){
            Sym4.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete4);
         }

         if(Sym5){
           Sym5.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete5);
         }

         if(Sym6){
            Sym6.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete6);
         }

         if(Ace){
           Ace.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
         }

          if(King){
            King.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
          }

          if(Queen){
             Queen.stop();
             createjs.Ticker.removeEventListener("tick",WinAnimComplete9);
          }

         if(Jack){
            Jack.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete10);
         }

         if(scatter){
            scatter.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete11);
         }

    };


    return instance;

};