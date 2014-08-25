package com.cwh.sampleslotgame.model.tests{

    import org.flexunit.Assert;
    import com.cwh.sampleslotgame.model.MainScreenModel;

    public class ModelTestCase {

        private var m:MainScreenModel;

        [Before]
        public function before () : void {
            this.m = new MainScreenModel( 1000.00, [0.01,0.05,0.25,1,2,5], 5 );
        }

        [Test]
        public function InitialState () : void {
            Assert.assertEquals(5,this.m.lines);
            Assert.assertEquals(5,this.m.bet);
            Assert.assertEquals(1,this.m.creditsPerLine);
            Assert.assertEquals(0,this.m.won);
            Assert.assertEquals(1000,this.m.balance);
            Assert.assertNull(this.m.reelStops);
            Assert.assertEquals(0,this.m.linesWon);
            Assert.assertEquals(0,this.m.winningLines.length);
            Assert.assertEquals(0,this.m.spinStartTime);
            Assert.assertEquals(0,this.m.starsTotal);
            Assert.assertEquals(0,this.m.freeSpinsTotal);
            Assert.assertEquals(0,this.m.freeSpinsUsed);
            Assert.assertEquals(0,this.m.freeSpinsWon); 
            Assert.assertFalse(this.m.freeSpinsTriggered);
            Assert.assertEquals(0,this.m.gameState);
        }

        [Test]
        public function resetForSpin () : void {
            this.m.resetForSpin();
            Assert.assertNull(this.m.reelStops);
            Assert.assertEquals(0,this.m.winningLines.length);
            Assert.assertEquals(0,this.m.won);
            Assert.assertFalse(this.m.freeSpinsTriggered);
        }

        [Test]
        public function resetAfterFreeSpinBonus () : void {
            this.m.resetAfterFreeSpinBonus();
            Assert.assertEquals(0,this.m.freeSpinsTotal);
            Assert.assertEquals(0,this.m.freeSpinsUsed);
            Assert.assertEquals(0,this.m.freeSpinsWon);
        }

        [Test]
        public function getLines () : void {
            Assert.assertEquals(5, this.m.lines);
        }

        [Test]
        public function getBet () : void {
            Assert.assertEquals(5, this.m.bet);
        }

        [Test]
        public function getCreditsPerLine () : void {
            Assert.assertEquals(1, this.m.creditsPerLine);
        }
        
        [Test]
        public function getBalance () : void {
            Assert.assertEquals(1000, this.m.balance);
        }

        [Test]
        public function setBalance () : void {
            this.m.balance = 500;
            Assert.assertEquals(500, this.m.balance);
        }

        [Test]
        public function getCredits () : void {
            Assert.assertEquals(100000, this.m.credits);
        }

        [Test]
        public function getCreditValue () : void {
            Assert.assertEquals(0.01, this.m.creditValue);
        }

        [Test]
        public function increaseCreditValue () : void {
            Assert.assertEquals(0.05, this.m.increaseCreditValue());
        }

        [Test]
        public function decreaseCreditValue () : void {
            Assert.assertEquals(0.01, this.m.decreaseCreditValue());
        }

        [Test]
        public function getWon () : void {
            Assert.assertEquals(0, this.m.won);
        }
        
        [Test]
        public function setWon () : void {
            this.m.won = 100;
            Assert.assertEquals(100, this.m.won);
        }
        
        [Test]
        public function getReelStops () : void {
            Assert.assertNull(this.m.reelStops);
        }

        [Test]
        public function setReelStops () : void {
            this.m.reelStops = [[1,2,3],[4,5,6],[7,8,9],[1,2,3],[4,5,6]];
            Assert.assertNotNull(this.m.reelStops);
            Assert.assertEquals(1,this.m.reelStops[0][0]);
            Assert.assertEquals(2,this.m.reelStops[0][1]);
            Assert.assertEquals(3,this.m.reelStops[0][2]);
            Assert.assertEquals(4,this.m.reelStops[1][0]);
            Assert.assertEquals(5,this.m.reelStops[1][1]);
            Assert.assertEquals(6,this.m.reelStops[1][2]);
            Assert.assertEquals(7,this.m.reelStops[2][0]);
            Assert.assertEquals(8,this.m.reelStops[2][1]);
            Assert.assertEquals(9,this.m.reelStops[2][2]);
            Assert.assertEquals(1,this.m.reelStops[3][0]);
            Assert.assertEquals(2,this.m.reelStops[3][1]);
            Assert.assertEquals(3,this.m.reelStops[3][2]);
            Assert.assertEquals(4,this.m.reelStops[4][0]);
            Assert.assertEquals(5,this.m.reelStops[4][1]);
            Assert.assertEquals(6,this.m.reelStops[4][2]);
        }

        [Test]
        public function getWinningLines () : void {
            Assert.assertEquals(0,this.m.winningLines.length);
        }

        [Test]
        public function setWinningLines () : void {
            this.m.winningLines = [1,2,3];
            Assert.assertEquals(3,this.m.winningLines.length);
        }

        [Test]
        public function getSpinStartTime () : void {
            Assert.assertEquals(0,this.m.spinStartTime);
        }

        [Test]
        public function setSpinStartTime () : void {
            this.m.spinStartTime = 5000;
            Assert.assertEquals(5000,this.m.spinStartTime);
        }

        [Test]
        public function getStarsTotal () : void {
            Assert.assertEquals(0,this.m.starsTotal);
        }

        [Test]
        public function setStarsTotal () : void {
            this.m.starsTotal = 50;
            Assert.assertEquals(50,this.m.starsTotal);
        }

        [Test]
        public function getFreeSpinsTotal () : void {
            Assert.assertEquals(0,this.m.freeSpinsTotal);
        }

        [Test]
        public function setFreeSpinsTotal () : void {
            this.m.freeSpinsTotal = 10;
            Assert.assertEquals(10,this.m.freeSpinsTotal);
        }

        [Test]
        public function getFreeSpinsUsed () : void {
            Assert.assertEquals(0,this.m.freeSpinsUsed);
        }

        [Test]
        public function setFreeSpinsUsed () : void {
            this.m.freeSpinsUsed = 10;
            Assert.assertEquals(10,this.m.freeSpinsUsed);
        }

        [Test]
        public function getFreeSpinsTriggered () : void {
            Assert.assertFalse(this.m.freeSpinsTriggered);
        }

        [Test]
        public function setFreeSpinsTriggered () : void {
            this.m.freeSpinsTriggered = true;
            Assert.assertTrue(this.m.freeSpinsTriggered);
        }

        [Test]
        public function getGameState () : void {
            Assert.assertEquals(0,this.m.gameState);
        }

        [Test]
        public function setGameState () : void {
            this.m.gameState = 1;
            Assert.assertEquals(1,this.m.gameState);
        }

        [Test]
        public function spinHasPaylineWinsFalse () : void {
            Assert.assertFalse(this.m.spinHasPaylineWins());
        }

        [Test]
        public function spinHasPaylineWinsTrue () : void {
            this.m.winningLines = [1,2,3];
            Assert.assertTrue(this.m.spinHasPaylineWins());
        }

        [Test]
        public function getLinesWon () : void {
            Assert.assertEquals(0,this.m.linesWon);
        }

        [Test]
        public function setLinesWon () : void {
            this.m.linesWon = 5;
            Assert.assertEquals(5,this.m.linesWon);
        }

    }

}