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
    var fireWolf;
    var freeOutro;
    var freeIntro;
    var freeRetrigg;
    var reelStrikeSound;
    var PreBigWinBlueSound;
    var PreBigWinRedSound;
    var wildSound, wildSound2, fwlogo, redwolf, bluewolf, firebird, paws, Ace, King, Queen, Jack, Ten, Nine, scatter, wildSoundMix;

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

    instance.PreBigWinBlueSound = function () {
        if (PreBigWinBlueSound) {
            PreBigWinBlueSound.stop();
            createjs.Ticker.removeEventListener("tick",PreBigWinBlueSoundComplete);
        }
        PreBigWinBlueSound = soundSystem.play(amaya.game.SoundConstants.PRE_BIGWIN_BLUE.id,0,amaya.game.SoundConstants.PRE_BIGWIN_BLUE.start);
        createjs.Ticker.addEventListener("tick",PreBigWinBlueSoundComplete);
    };

    var PreBigWinBlueSoundComplete = function(e){
        var currPos = PreBigWinBlueSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.PRE_BIGWIN_BLUE.end){
            if(amaya.game.SoundConstants.PRE_BIGWIN_BLUE.loop){
                PreBigWinBlueSound.setPosition(amaya.game.SoundConstants.PRE_BIGWIN_BLUE.start);
            }else{
                PreBigWinBlueSound.stop();
                createjs.Ticker.removeEventListener("tick",PreBigWinBlueSoundComplete);
            }
        }
    };

    instance.PreBigWinRedSound = function () {
        if (PreBigWinRedSound) {
            PreBigWinRedSound.stop();
            createjs.Ticker.removeEventListener("tick",PreBigWinRedSoundComplete);
        }
        PreBigWinRedSound = soundSystem.play(amaya.game.SoundConstants.PRE_BIGWIN_RED.id,0,amaya.game.SoundConstants.PRE_BIGWIN_RED.start);
        createjs.Ticker.addEventListener("tick",PreBigWinRedSoundComplete);
    };

    var PreBigWinRedSoundComplete = function(e){
        var currPos = PreBigWinRedSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.PRE_BIGWIN_RED.end){
            if(amaya.game.SoundConstants.PRE_BIGWIN_RED.loop){
                PreBigWinRedSound.setPosition(amaya.game.SoundConstants.PRE_BIGWIN_RED.start);
            }else{
                PreBigWinRedSound.stop();
                createjs.Ticker.removeEventListener("tick",PreBigWinRedSoundComplete);
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

     instance.PlayFireWolfSound = function () {
            if(fireWolf){
                 fireWolf.stop();
                 createjs.Ticker.removeEventListener("tick",fireWolfComplete);
            }
            fireWolf = soundSystem.play(amaya.game.SoundConstants.FIREWOLF.id,0,amaya.game.SoundConstants.FIREWOLF.start);
            createjs.Ticker.addEventListener("tick",fireWolfComplete);
     };

    var fireWolfComplete = function(e){
        var currPos = fireWolf.getPosition();
        if (currPos >= amaya.game.SoundConstants.FIREWOLF.end){
            if(amaya.game.SoundConstants.FIREWOLF.loop){
                fireWolf.setPosition(amaya.game.SoundConstants.FIREWOLF.start);
            }else{
                fireWolf.stop();
                createjs.Ticker.removeEventListener("tick",fireWolfComplete);
            }
        }
    };

    instance.StopFireWolfSound = function () {
        if (fireWolf) {
            fireWolf.stop();
            createjs.Ticker.removeEventListener("tick",fireWolfComplete);
        }
    };

    instance.PlayFreeSpinIntroSound = function () {
            if(freeIntro){
                 freeIntro.stop();
                 createjs.Ticker.removeEventListener("tick",fireWolfComplete);
            }
            freeIntro = soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_INTRO.id,0,amaya.game.SoundConstants.FREE_SPIN_INTRO.start);
            createjs.Ticker.addEventListener("tick",freeSpinIntroComplete);
     };

    var freeSpinIntroComplete = function(e){
        var currPos = freeIntro.getPosition();
        if (currPos >= amaya.game.SoundConstants.FREE_SPIN_INTRO.end){
            if(amaya.game.SoundConstants.FREE_SPIN_INTRO.loop){
                freeIntro.setPosition(amaya.game.SoundConstants.FREE_SPIN_INTRO.start);
            }else{
                freeIntro.stop();
                createjs.Ticker.removeEventListener("tick",freeSpinIntroComplete);
            }
        }
    };

    instance.StopFreeSpinIntroSound = function () {
        if (freeIntro) {
            freeIntro.stop();
            createjs.Ticker.removeEventListener("tick",freeSpinIntroComplete);
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
        if(wildSound){
           wildSound.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete1);
        }
        wildSound = soundSystem.play(amaya.game.SoundConstants.WILD.id,0,amaya.game.SoundConstants.WILD.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete1);
    };

    var WinAnimComplete1 = function(e){
        var currPos = wildSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.WILD.end){
            if(amaya.game.SoundConstants.WILD.loop){
                wildSound.setPosition(amaya.game.SoundConstants.WILD.start);
            }else{
                wildSound.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete1);
            }
        }
    };

    var logoSound = function () {
        if(fwlogo){
          fwlogo.stop();
          createjs.Ticker.removeEventListener("tick",WinAnimComplete2);
        }

        fwlogo = soundSystem.play(amaya.game.SoundConstants.LOGO.id,0,amaya.game.SoundConstants.LOGO.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete2);
    };

    var WinAnimComplete2 = function(e){
        var currPos = fwlogo.getPosition();
        if (currPos >= amaya.game.SoundConstants.LOGO.end){
            if(amaya.game.SoundConstants.LOGO.loop){
                fwlogo.setPosition(amaya.game.SoundConstants.LOGO.start);
            }else{
                fwlogo.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete2);
            }
        }
    };

    var redWolfSound = function () {
        if(redwolf){
           redwolf.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete3);
        }
        redwolf = soundSystem.play(amaya.game.SoundConstants.REDWOLF.id,0,amaya.game.SoundConstants.REDWOLF.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete3);
    };

    var WinAnimComplete3 = function(e){
        var currPos = redwolf.getPosition();
        if (currPos >= amaya.game.SoundConstants.REDWOLF.end){
            if(amaya.game.SoundConstants.REDWOLF.loop){
                redwolf.setPosition(amaya.game.SoundConstants.REDWOLF.start);
            }else{
                redwolf.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete3);
            }
        }
    };

    var blueWolfSound = function () {
        if(bluewolf){
            bluewolf.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete4);
        }
        bluewolf = soundSystem.play(amaya.game.SoundConstants.BLUEWOLF.id,0,amaya.game.SoundConstants.BLUEWOLF.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete4);
    };

    var WinAnimComplete4 = function(e){
        var currPos = bluewolf.getPosition();
        if (currPos >= amaya.game.SoundConstants.BLUEWOLF.end){
            if(amaya.game.SoundConstants.BLUEWOLF.loop){
                bluewolf.setPosition(amaya.game.SoundConstants.BLUEWOLF.start);
            }else{
                bluewolf.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete4);
            }
        }
    };

    var fireBirdSound = function () {
        if(firebird){
           firebird.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete5);
        }

        firebird = soundSystem.play(amaya.game.SoundConstants.FIREBIRD.id,0,amaya.game.SoundConstants.FIREBIRD.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete5);
        //alert("sound position: "+firebird.getPosition());
    };

    var WinAnimComplete5 = function(e){
        var currPos = firebird.getPosition();
        if (currPos >= amaya.game.SoundConstants.FIREBIRD.end){
            if(amaya.game.SoundConstants.FIREBIRD.loop){
                firebird.setPosition(amaya.game.SoundConstants.FIREBIRD.start);
            }else{
                firebird.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete5);
            }
        }
    };

    var pawsSound = function () {
        if(paws){
           paws.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete6);
        }

        paws = soundSystem.play(amaya.game.SoundConstants.PAWS.id,0,amaya.game.SoundConstants.PAWS.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete6);
        //alert("sound position: "+paws.getPosition());
    };

    var WinAnimComplete6 = function(e){
        var currPos = paws.getPosition();
        if (currPos >= amaya.game.SoundConstants.PAWS.end){
            if(amaya.game.SoundConstants.PAWS.loop){
                paws.setPosition(amaya.game.SoundConstants.PAWS.start);
            }else{
                paws.stop();
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

    var symSound = [wildSound,logoSound,redWolfSound,blueWolfSound,fireBirdSound,pawsSound,aceSound,kingSound,queenSound,jackSound,bonusSound];

    instance.PlayWinAnim = function (sumId) {
        //alert("Call PlayWinAnim");
        symSound[sumId-1]();
    };


    return instance;

};